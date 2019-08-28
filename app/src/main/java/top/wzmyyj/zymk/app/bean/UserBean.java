package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/06/23. email: 2209011667@qq.com
 */

public class UserBean {

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
     */

    private String id;
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

    public UserBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
