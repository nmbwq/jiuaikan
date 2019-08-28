package top.wzmyyj.zymk.app.bean;

import java.io.Serializable;

public class SelectOneBean implements Serializable {


    /**
     * ret : 200
     * data : {"err_code":0,"err_msg":"","data":{"id":1,"uuid":"","add_time":"2019-08-17 13:57:51","update_time":"2019-08-17 14:24:39","ext_data":null,"phone":"18668121550","username":"我爱中国","pwd":"123456","age":"\u201c\u201d","xingzuo":"\u201c\u201d","image":"\u201c\u201d","sex":"\u201c\u201d","description":"\u201c\u201d"}}
     * msg : 当前小白接口：App.Table.Get
     */

    private int ret;
    private DataBeanX data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBeanX {
        /**
         * err_code : 0
         * err_msg :
         * data : {"id":1,"uuid":"","add_time":"2019-08-17 13:57:51","update_time":"2019-08-17 14:24:39","ext_data":null,"phone":"18668121550","username":"我爱中国","pwd":"123456","age":"\u201c\u201d","xingzuo":"\u201c\u201d","image":"\u201c\u201d","sex":"\u201c\u201d","description":"\u201c\u201d"}
         */

        private int err_code;
        private String err_msg;
        private DataBean data;

        public int getErr_code() {
            return err_code;
        }

        public void setErr_code(int err_code) {
            this.err_code = err_code;
        }

        public String getErr_msg() {
            return err_msg;
        }

        public void setErr_msg(String err_msg) {
            this.err_msg = err_msg;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 1
             * uuid :
             * add_time : 2019-08-17 13:57:51
             * update_time : 2019-08-17 14:24:39
             * ext_data : null
             * phone : 18668121550
             * username : 我爱中国
             * pwd : 123456
             * age : “”
             * xingzuo : “”
             * image : “”
             * sex : “”
             * description : “”
             */

            private int id;
            private String uuid;
            private String add_time;
            private String update_time;
            private Object ext_data;
            private String phone;
            private String username;
            private String pwd;
            private String age;
            private String xingzuo;
            private String image;
            private String sex;
            private String description;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public Object getExt_data() {
                return ext_data;
            }

            public void setExt_data(Object ext_data) {
                this.ext_data = ext_data;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPwd() {
                return pwd;
            }

            public void setPwd(String pwd) {
                this.pwd = pwd;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getXingzuo() {
                return xingzuo;
            }

            public void setXingzuo(String xingzuo) {
                this.xingzuo = xingzuo;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
