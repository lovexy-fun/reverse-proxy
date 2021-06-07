package fun.lovexy.reverseproxy.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Mapping implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String SELF_PORT = "self_port";
    public static final String PROXY_IP = "proxy_ip";
    public static final String PROXY_PORT = "proxy_port";
    public static final String PROXY_DESCRIPTION = "proxy_description";
    public static final String STATUS = "status";
    public static final String CREATE_TIMESTAMP = "create_timestamp";
    public static final String MODIFY_TIMESTAMP = "modify_timestamp";

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer selfPort;

    private String proxyIp;

    private Integer proxyPort;

    private String proxyDescription;

    private Integer status;

    private Date createTimestamp;

    private Date modifyTimestamp;

}