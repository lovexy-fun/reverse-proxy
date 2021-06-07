package fun.lovexy.reverseproxy.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ConfVO {

    private Integer selfPort;

    private String proxyIp;

    private Integer proxyPort;

    private List<String> allowIps;

}
