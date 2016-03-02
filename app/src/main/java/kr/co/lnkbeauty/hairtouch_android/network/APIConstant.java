package kr.co.lnkbeauty.hairtouch_android.network;

/**
 * Created by leetaejun on 2016. 3. 2..
 */
public class APIConstant {
    // API 루트 주소
    public static final String URI_REQUEST_ROOT = "http://localhost:3000";
    // API 요청 주소
    public static final String URI_REQUEST_API = "http://localhost:3000/api/";

    // Designer 정보 요청하기
    public static final String URI_DESIGNER_INFO = "users.json";
    // Designer 정보 수정하기
    public static final String URI_DESIGNER_MODIFY_INFO = "users/modify.json";
    // Designer Authentication
    public static final String URI_DESIGNER_AUTHENTICATION = "users/authentication.json";
    // Designer SignUp
    public static final String URI_DESIGNER_SIGN_UP = "users/signup.json";
    // Designer SignIn
    public static final String URI_DESIGNER_SIGN_IN = "users/signin.json";
}
