package fun.lovexy.reverseproxy.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import fun.lovexy.reverseproxy.entity.Access;
import fun.lovexy.reverseproxy.entity.ConfVO;
import fun.lovexy.reverseproxy.entity.Mapping;
import fun.lovexy.reverseproxy.service.AccessService;
import fun.lovexy.reverseproxy.service.CommandService;
import fun.lovexy.reverseproxy.service.MappingService;
import fun.lovexy.reverseproxy.service.NginxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

@Slf4j
@Service
public class NginxServiceImpl implements NginxService {

    @Value("${nginx.home}")
    private String nginxHome;

    @Autowired
    private MappingService mappingService;

    @Autowired
    private AccessService accessService;

    @Autowired
    private CommandService commandService;

    private void fmGen(Map<String, Object> data) throws Exception {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setEncoding(Locale.getDefault(), "UTF-8");
        configuration.setNumberFormat("#");
        configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/");
        Template template = configuration.getTemplate("reverse-proxy.conf.ftl");
        File file = new File(nginxHome + File.separator + "conf" + File.separator + "reverse-proxy.conf");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        template.process(data, bufferedWriter);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private void generateConf() throws Exception {
        List<Mapping> mappings = mappingService.enabledList();
        List<Access> accesses = accessService.list();
        List<ConfVO> confs = new LinkedList<>();
        for (Mapping mapping : mappings) {
            ConfVO confVO = new ConfVO();
            confVO.setSelfPort(mapping.getSelfPort());
            confVO.setProxyIp(mapping.getProxyIp());
            confVO.setProxyPort(mapping.getProxyPort());
            List<String> allowIps = new LinkedList<>();
            accesses.stream()
                    .filter(access -> access.getMappingId() == mapping.getId())
                    .forEach(access -> allowIps.add(access.getAllowIp()));
            confVO.setAllowIps(allowIps);
            confs.add(confVO);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("confs", confs);
        fmGen(data);
    }

    @Override
    public boolean isRunning() {

        String path = String.format("%s%slogs%snginx.pid", nginxHome, File.separator, File.separator);
        File file = new File(path);
        return file.exists() && file.length() > 0;

    }

    @Override
    public boolean reload() {

        try {
            this.generateConf();
            List<String> cmd = new LinkedList<>();
            cmd.add(nginxHome + File.separator + "nginx");
            cmd.add("-s");
            cmd.add("reload");
            String result = commandService.execute(new File(nginxHome), cmd);
            return StringUtils.isEmpty(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }

    }

    @Override
    public boolean stop() {

        List<String> cmd = new LinkedList<>();
        cmd.add(nginxHome + File.separator + "nginx");
        cmd.add("-s");
        cmd.add("stop");
        String result = commandService.execute(new File(nginxHome), cmd);
        return StringUtils.isEmpty(result);

    }

    @Override
    public boolean start() {

        try {
            this.generateConf();

            List<String> cmd = new LinkedList<>();
            cmd.add(nginxHome + File.separator + "nginx");
            Thread nginxThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    commandService.execute(new File(nginxHome), cmd);
                }
            });
            nginxThread.start();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }

    }

}
