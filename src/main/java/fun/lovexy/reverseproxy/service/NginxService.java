package fun.lovexy.reverseproxy.service;

public interface NginxService {

    boolean isRunning();

    boolean reload();

    boolean stop();

    boolean start();

}
