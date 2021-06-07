package fun.lovexy.reverseproxy.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class R<T> implements Serializable {

    private int code;

    private boolean success;

    private String msg;

    private T data;

    private R() {}

    private R(Status status) {
        code = status.code();
        msg = status.msg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setStatus(Status status) {
        this.success = status.code == Status.SUCCESS.code();
        this.code = status.code();
    }

    public static <T> R<T> newOne() {
        return new R<T>();
    }

    private static <T> R<T> newOne(Status status, boolean success) {
        R<T> R = new R<>(status);
        R.setSuccess(success);
        return R;
    }

    public static <T> R<T> success() {
        return newOne(Status.SUCCESS, true);
    }

    public static <T> R<T> error() {
        return newOne(Status.ERROR, false);
    }

    public static <T> R<T> fail() {
        return newOne(Status.FAIL, false);
    }

    public static <T> R<T> exception() {
        return newOne(Status.EXCEPTION, false);
    }

    public static <T> R<T> noPerm() {
        return newOne(Status.NO_PERM, false);
    }

    public void appendMsg(String msg) {
        if (StringUtils.isEmpty(msg)) {
            this.msg = msg;
        } else {
            this.msg += "," + msg;
        }
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public enum Status {

        SUCCESS(0, "成功"),
        FAIL(1, "失败"),
        ERROR(2, "错误"),
        EXCEPTION(3, "系统异常"),
        NO_PERM(4, "没有权限");

        private int code;

        private String msg;

        Status(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int code() {
            return this.code;
        }

        public String msg() {
            return this.msg;
        }

    }

}
