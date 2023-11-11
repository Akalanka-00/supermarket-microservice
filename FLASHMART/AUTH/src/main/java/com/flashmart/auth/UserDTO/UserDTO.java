package com.flashmart.auth.UserDTO;

public class UserDTO {

    private long userid;
    private int type;
    private String fname;
    private String lname;
    private String mobile;
    private String email;
    private String password;

    public UserDTO(long userid, int userType, String userfname, String userlname, String mobile, String email, String password) {
        this.userid = userid;
        this.type = userType;
        this.fname = userfname;
        this.lname = userlname;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public UserDTO() {
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserfname() {
        return fname;
    }

    public void setUserfname(String userfname) {
        this.fname = fname;
    }

    public String getUserlname() {
        return lname;
    }

    public void setUserlname(String userlname) {
        this.lname = userlname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userid=" + userid +
                ", userType=" + type +
                ", userfname='" + lname + '\'' +
                ", userlname='" + lname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


