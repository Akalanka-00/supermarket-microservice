package com.flashmart.auth.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "User")
public class User {

    @Id
    @Column(name="user_id", length = 45)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userid;
    @Column(name = "userType",length = 10)
    private int type;//1000- admin , 1010- normalUser, 1011- deliveryPerson
    @Column(name="user_fname", length = 255)
    private String fname;
    @Column(name="user_lname", length = 255)
    private String lname;
    @Column(name="mobile", length = 10)
    private String mobile;
    @Column(name="email", length = 255, unique = true)
    private String email;
    @Column(name="password", length = 255)
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", userType=" + type +
                ", userfname='" + fname + '\'' +
                ", userlname='" + lname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
