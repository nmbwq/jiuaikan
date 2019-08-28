package top.wzmyyj.zymk.app.bean;

import java.io.Serializable;

public class firstBean implements Serializable {

    /**
     * code : 200
     * data : false
     * msg :
     * success : true
     */

    private int code;
    private boolean data;
    private String msg;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
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
