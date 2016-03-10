package kr.co.lnkbeauty.hairtouch_android.model;

import java.io.Serializable;

/**
 * Created by leetaejun on 2016. 3. 10..
 */
public class CommonCodeModel implements Serializable {
    private int id;
    private String main_code;
    private int detail_code;
    private String detail_name;

    public int getId() {
        return id;
    }

    public String getMain_code() {
        return main_code;
    }

    public int getDetail_code() {
        return detail_code;
    }

    public String getDetail_name() {
        return detail_name;
    }
}
