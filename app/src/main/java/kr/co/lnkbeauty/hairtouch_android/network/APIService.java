package kr.co.lnkbeauty.hairtouch_android.network;

import kr.co.lnkbeauty.hairtouch_android.model.AuthenticationModel;
import kr.co.lnkbeauty.hairtouch_android.model.DesignerModel;
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
    Call<DesignerModel> loadDesignerInfo(@Query("access_token") String access_token);

    @POST(APIConstant.URI_DESIGNER_MODIFY_INFO)
    Call<AuthenticationModel> modifyDesignerInfo(@Body DesignerModel user);

    @FormUrlEncoded
    @POST(APIConstant.URI_DESIGNER_AUTHENTICATION)
    Call<AuthenticationModel> loadAuthentication(@Field("access_token") String access_token);

    @POST(APIConstant.URI_DESIGNER_SIGN_UP)
    Call<AuthenticationModel> signupDesigner(@Body DesignerModel user);

    @FormUrlEncoded
    @POST(APIConstant.URI_DESIGNER_SIGN_IN)
    Call<AuthenticationModel> signinDesigner(@Field("email") String email, @Field("password") String password);
}
