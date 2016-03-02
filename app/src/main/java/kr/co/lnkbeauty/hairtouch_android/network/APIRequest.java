package kr.co.lnkbeauty.hairtouch_android.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by leetaejun on 2016. 3. 2..
 */
public class APIRequest {

    private static APIRequest requester;

    private Retrofit retrofit;

    private APIRequest() {
        retrofit = new Retrofit.Builder()
                .baseUrl(APIConstant.URI_REQUEST_API).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static APIRequest getInstacne() {
        if (requester == null) {
            requester = new APIRequest();
        }
        return requester;
    }

    public APIService getService() {
        return retrofit.create(APIService.class);
    }
}
