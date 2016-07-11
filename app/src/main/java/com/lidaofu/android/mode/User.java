package com.lidaofu.android.mode;

/**
 * Created by LiDaofu on 16/7/10.
 */
public class User {

    public String userName;
    public String userPass;

    public User(){}

    public User(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
