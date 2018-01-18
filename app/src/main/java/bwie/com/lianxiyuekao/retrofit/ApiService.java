package bwie.com.lianxiyuekao.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface ApiService {
    /**
     * GET请求
     * @param url
     * @param map
     * @return
     */
    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, String> map);

    /**
     * POST请求
     * @param url
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, String> map);

}
