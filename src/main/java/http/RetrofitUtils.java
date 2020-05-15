package http;


import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtils {


    private static Retrofit showRetrofit;
    private static Retrofit upRetrofit;


//    private static Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("http://47.105.173.231:8080/")
////            .baseUrl("http://47.105.173.231:8080/android/")
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build();


    public static Retrofit getShowRetrofit() {
        if (showRetrofit == null) {
//            showRetrofit = newRetrofit("http://47.105.173.231:8080/");
        	showRetrofit = newRetrofit("http://localhost:8080/ShowInfo/");
        }
        return showRetrofit;
    }

    public static Retrofit getUpRetrofit() {
        if (upRetrofit == null) {
            upRetrofit = newRetrofit("http://msg.umeng.com/api/");
        }
        return upRetrofit;
    }

    public static Retrofit newRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
//            .baseUrl("http://47.105.173.231:8080/android/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }


}
