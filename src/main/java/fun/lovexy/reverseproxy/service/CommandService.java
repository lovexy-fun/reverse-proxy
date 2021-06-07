package fun.lovexy.reverseproxy.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public interface CommandService {

    default String execute(File dir, String... cmd) {
        return execute(dir, Arrays.asList(cmd));
    }

    default String execute(String... cmd) {
        return execute(null, Arrays.asList(cmd));
    }

    default String execute(List<String> cmd) {
        return execute(null, cmd);
    }

    String execute(File dir, List<String> cmd);

}
