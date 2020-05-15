package http;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HttpApi {

	// @Headers({"Content-Type: application/json"})
	// @GET("ShowInfo/ShowAll?p=123456")
	// Observable<String> showAll(@Query("email") String name, @Query("pwd") String
	// pwd);
	//
	// @Headers({"Content-Type: application/json"})
	// @POST("ShowInfo/ShowAll?sign=123456")
	// Observable<String> showAllPost(@Query("param") String param, @Body
	// RequestBody body);
	//
	//
	//// @GET("SignUp")
	//// Observable<BaseHttp> signUp(@Query("name") String name, @Query("email")
	// String email, @Query("vercode") String vercode, @Query("pwd") String pwd);
	////
	//// @GET("SendVercode")
	//// Observable<BaseHttp<String>> sendVercode(@Query("email") String name);
	//
	//
	// @Headers({"Content-Type: application/json"})
	// @POST("send")
	// Observable<String> sendMsg(@Query("sign") String sign, @Body RequestBody
	// body);

	@Headers({ "Content-Type: application/json;charset=utf-8" })
	@POST("send")
	Call<String> sendMsg(@Query("sign") String sign, @Body RequestBody body);

	@Headers({ "Content-Type: application/json" })
	@GET("ShowAll?p=123456")
	Call<String> showAll(@Query("email") String name, @Query("pwd") String pwd);
}
