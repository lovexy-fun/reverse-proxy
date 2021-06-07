package fun.lovexy.reverseproxy.controller;

import fun.lovexy.reverseproxy.common.R;
import fun.lovexy.reverseproxy.service.NginxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/nginx")
public class NginxController {

    @Autowired
    private NginxService nginxService;

    @RequestMapping("/status")
    @ResponseBody
    public R<Boolean> isRunning() {
        R<Boolean> success = R.success();
        success.setData(nginxService.isRunning());
        return success;
    }

    @RequestMapping("/stop")
    @ResponseBody
    public R<Boolean> stop() {
        R<Boolean> success = R.success();
        success.setData(nginxService.stop());
        return success;
    }

    @RequestMapping("/start")
    @ResponseBody
    public R<Boolean> start() {
        R<Boolean> success = R.success();
        success.setData(nginxService.start());
        return success;
    }

    @RequestMapping("/reload")
    @ResponseBody
    public R<Boolean> reload() {
        R<Boolean> success = R.success();
        success.setData(nginxService.reload());
        return success;
    }

}
