package top.wzmyyj.zymk.app.bean;

import java.io.Serializable;

public class GetUrlBean implements Serializable {

    /**
     * code : 200
     * data : false
     * msg :
     * success : true
     */

    private int code;
    private String data;
    private String msg;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
