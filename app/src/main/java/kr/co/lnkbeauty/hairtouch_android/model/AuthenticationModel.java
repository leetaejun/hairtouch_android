package kr.co.lnkbeauty.hairtouch_android.model;

import java.io.Serializable;

/**
 * Created by leetaejun on 2016. 2. 14..
 */
public class AuthenticationModel implements Serializable {
    private String access_token;
    private DesignerModel designer;

    public String getAccess_token() {
        return access_token;
    }

    public DesignerModel getDesigner() {
        return designer;
    }
}
