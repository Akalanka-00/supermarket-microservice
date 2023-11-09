package com.flashmart.auth.Entity;

import jakarta.persistence.*;

@Entity
@Table (name = "User")
public class User {

    @Id
    @Column(name="user_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;
    @Column(name = "userType",length = 10)
    private int type;//1000- admin , 1010- normalUser
    @Column(name="user_fname", length = 255)
    private String userfname;
    @Column(name="user_lname", length = 255)
    private String userlname;
    @Column(name="mobile", length = 10)
    private String mobile;
    @Column(name="email", length = 255)
    private String email;
    @Column(name="password", length = 255)
    private String password;

    public User(int userid, int userType, String userfname, String userlname, String mobile, String email, String password) {
        this.userid = userid;
        this.type = userType;
        this.userfname = userfname;
        this.userlname = userlname;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserfname() {
        return userfname;
    }

    public void setUserfname(String userfname) {
        this.userfname = userfname;
    }

    public String getUserlname() {
        return userlname;
    }

    public void setUserlname(String userlname) {
        this.userlname = userlname;
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
        return "User{" +
                "userid=" + userid +
                ", userType=" + type +
                ", userfname='" + userfname + '\'' +
                ", userlname='" + userlname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
