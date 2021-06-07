package fun.lovexy.reverseproxy.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Access implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String MAPPING_ID = "mapping_id";
    public static final String ALLOW_IP = "allow_ip";
    public static final String STATUS = "status";
    public static final String CREATE_TIMESTAMP = "create_timestamp";
    public static final String MODIFY_TIMESTAMP = "modify_timestamp";

    private Integer mappingId;

    private String allowIp;

    private Integer status;

    private Date createTimestamp;

    private Date modifyTimestamp;

}