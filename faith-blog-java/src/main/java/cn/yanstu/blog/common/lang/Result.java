package cn.yanstu.blog.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yanstu
 * 统一结果封装
 * 是否成功，可用code表示（如200表示成功，400表示异常）
 */
@Data
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;

    public static Result succ(Object data) {
        return succ(200, "操作成功", data);
    }

    public static Result succ(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
