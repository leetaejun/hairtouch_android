package kr.co.lnkbeauty.hairtouch_android.network;

/**
 * Created by leetaejun on 2016. 3. 2..
 */
public class APIConstant {
    // API 루트 주소
    public static final String URI_REQUEST_ROOT = "http://192.168.1.151:3000";
    // API 요청 주소
    public static final String URI_REQUEST_API = "http://192.168.1.151:3000/api/";

    // 공통코드 요청하기
    public static final String URI_COMMON_CODES = "common_codes.json";

    // Designer 정보 요청하기
    public static final String URI_DESIGNER_INFO = "designers.json";
    // Designer 정보 수정하기
    public static final String URI_DESIGNER_MODIFY_INFO = "designers/modify.json";
    // Designer Authentication
    public static final String URI_DESIGNER_AUTHENTICATION = "designers/authentication.json";
    // Designer SignUp
    public static final String URI_DESIGNER_SIGN_UP = "designers/signup.json";
    // Designer SignIn
    public static final String URI_DESIGNER_SIGN_IN = "designers/signin.json";
}
