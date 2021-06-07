package fun.lovexy.reverseproxy.service.impl;

import fun.lovexy.reverseproxy.service.CommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Slf4j
@Service
public class CommandServiceImpl implements CommandService {

    @Override
    public String execute(File dir, List<String> cmd) {
        Process process = null;
        try {
            process = new ProcessBuilder(cmd).directory(dir).start();
            int exitValue = process.waitFor();
            if (exitValue > 0) {
                return reader(process.getErrorStream());
            } else {
                return reader(process.getInputStream());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return null;
    }

    private String reader(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
