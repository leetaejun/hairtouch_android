package kr.co.lnkbeauty.hairtouch_android.model;

import java.io.Serializable;

/**
 * Created by leetaejun on 2016. 2. 12..
 */
public class DesignerModel implements Serializable {

    private int id;
    private String email;
    private String password;
    private String nickname;
    private ImageModel image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ImageModel getImage() {
        return image;
    }

    public void setImage(ImageModel image) {
        this.image = image;
    }
}
