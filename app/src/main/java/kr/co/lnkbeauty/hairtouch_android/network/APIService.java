package kr.co.lnkbeauty.hairtouch_android.network;

import kr.co.lnkbeauty.hairtouch_android.model.AuthenticationModel;
import kr.co.lnkbeauty.hairtouch_android.model.UserModel;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by leetaejun on 2016. 3. 2..
 */
public interface APIService {

    @GET(APIConstant.URI_DESIGNER_INFO)
    Call<UserModel> loadUserInfo(@Query("access_token") String access_token);

    @POST(APIConstant.URI_DESIGNER_MODIFY_INFO)
    Call<AuthenticationModel> modifyUserInfo(@Body UserModel user);

    @FormUrlEncoded
    @POST(APIConstant.URI_DESIGNER_AUTHENTICATION)
    Call<AuthenticationModel> loadAuthentication(@Field("access_token") String access_token);

    @POST(APIConstant.URI_DESIGNER_SIGN_UP)
    Call<AuthenticationModel> signupUser(@Body UserModel user);

    @FormUrlEncoded
    @POST(APIConstant.URI_DESIGNER_SIGN_IN)
    Call<AuthenticationModel> signinUser(@Field("email") String email, @Field("password") String password);
}
