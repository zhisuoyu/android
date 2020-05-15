package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import bean.chat.Body;
import bean.chat.Custom;
import bean.chat.PayLoad;
import bean.chat.Policy;
import bean.chat.SendInfo;
import bean.chat.UniInfo;
import bean.http.BaseHttp;
import cst.DbCst;
import cst.HttpCst;
import http.HttpApi;
import http.RetrofitUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;
import tools.MysqlHelper;
import utils.EncryptUtils;
import utils.TimeUtils;
import utils.UUID22;

public class UpushUtils {
	// Test
	// public static final String APP_KEY = "5dfb0dfbcb23d21f35000057";
	// public static final String MSG_SECRET = "6581076ea4091e736597c7f016c62c06";
	// public static final String MASTER_SECRET =
	// "oramb77wtcbyw7u3dzgnxb5rqpzz9zvo";

	public static String ASD = "阿斯顿";
	
	// Sum
	public static final String APP_KEY = "5e0ab9fb4ca35742b600066c";
	// // public static final String MSG_SECRET =
	// "28a0a07ced358d937709af01615ad040";
	public static final String MASTER_SECRET = "q7baeqrbwnbwnn2bqzykr4a1r3jgaxpq";

	// val APP_KEY = ""
	// val MSG_SECRET = "28a0a07ced358d937709af01615ad040"
	// val MASTER_SECRET = "q7baeqrbwnbwnn2bqzykr4a1r3jgaxpq"
	// val UPUSH_SEND_URL = "http://msg.umeng.com/api/send"

	public static final String UPUSH_SEND_URL = "http://msg.umeng.com/api/send";

//	public static void test() throws ClassNotFoundException, SQLException {
//		Custom custom = new Custom();
//		custom.setDt(System.currentTimeMillis());
//		custom.setFrom("哈哈");
//		custom.setMsg("1704");
////		System.out.println(sendUniMsg(custom, "Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X"));
//		System.out.println(sendUniMsg(custom,queryTokens("12345678")));
//	}
	
	 
    public static void test() throws IOException {
    	String body = "{\"device_tokens\":\"Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X\",\"appkey\":\"5e0ab9fb4ca35742b600066c\",\"timestamp\":\"1577781685590\",\"type\":\"unicast\",\"payload\":{\"display_type\":\"message\",\"body\":{\"custom\":{\"from\":\"mzs\",\"dt\":1577781685588,\"msg\":\"我\"}}},\"policy\":{\"expire_time\":\"2020-01-03 16:41:25\"}}";//"{\"device_tokens\":\"Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X\",\"appkey\":\"5e0ab9fb4ca35742b600066c\",\"timestamp\":\"1577781180454\",\"type\":\"unicast\",\"payload\":{\"display_type\":\"message\",\"body\":{\"custom\":{\"from\":\"哈哈\",\"dt\":1577781180052,\"msg\":\"1704\"}}},\"policy\":{\"expire_time\":\"2020-01-03 16:33:00\"}}";
    	String sign = "e9497cf6efff9f429956e1609e3ae85e";//"da334074e745aefe80436861070a0dd3";
    	Response<String> response = RetrofitUtils.getUpRetrofit()
				.create(HttpApi.class)
				.sendMsg(sign, RequestBody.create(MediaType.get("application/json; charset=utf-8"), body))
				.execute();

		int code = response.code();
		String msg = code == HttpCst.CODE_200 ? HttpCst.MSG_200 : response.errorBody().string();
		System.out.println(msg);
    }
    
    public static void t() throws IOException {
    	String token ="Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X";
    	
		UniInfo info = new UniInfo();
		info.setDevice_tokens(token);
		info.setAppkey(APP_KEY);
		info.setType("unicast");
		

		String timestamp = "1577782694506";//System.currentTimeMillis()+"";
		System.out.println(timestamp);
		info.setTimestamp(timestamp);
		
		
		Custom custom = new Custom();
//		custom.setMsg("你好");
//		custom.setDt(1577782694506l);
	
		Body<Custom> customBody = new Body<>();
		customBody.setCustom(custom);
		PayLoad payLoad = new PayLoad();
		payLoad.setBody(customBody);
		info.setPayload(payLoad);
		String body = new Gson().toJson(info);
		System.out.println(body);
    	String sign = createSign(body);
    	System.out.println(sign);
    	Response<String> response = RetrofitUtils.getUpRetrofit()
				.create(HttpApi.class)
				.sendMsg(sign, RequestBody.create(MediaType.get("application/json; charset=utf-8"), body))
				.execute();

		int code = response.code();
		String msg = code == HttpCst.CODE_200 ? HttpCst.MSG_200 : response.errorBody().string();
		System.out.println(msg);
    }
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		t();
//		
//		try {
////			System.out.println();
//			sen
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		// String body = createUnicastMsg(custom);
		// System.out.println(body);
		// String sign = createSign(body);
		// try {
		// Response<String> response = RetrofitUtils.getUpRetrofit()
		// .create(HttpApi.class)
		// .sendMsg(sign, RequestBody.create(MediaType.get("application/json;
		// charset=utf-8"), body))
		// .execute();
		//
		// int code= response.code();
		// String msg = code ==
		// HttpConst.CODE_200?HttpConst.MSG_200:response.errorBody().string();
		// System.out.println(msg);
		//// bh.setCode(response.code());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// test();
		// sendBroadcast();
		// Custom custom = new Custom();
		// custom.setDt(System.currentTimeMillis());
		// custom.setFrom("maozs");
		// custom.setMsg("1704");
		// BaseHttp<Void> bh = sendBroadcast(custom);
		// System.out.println(bh.toString());

		// try {
		// System.out.println(addUpushTokenIfNotExist("11111"));
		// } catch (ClassNotFoundException | SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
	
	public static void addToDb(String token,String msg) throws Exception {
		ResultSet resultSet=MysqlHelper.getInstance().executeQuery("select id from "+DbCst.TB_NAME_PUSH_TOKEN+" where token='"+token+"'");
		if(resultSet.next()) {
			int token_id = resultSet.getInt("id");
			String msg_id = UUID22.random().toString();
			MysqlHelper.getInstance().execute("insert into msg(token_id,msg,msg_id) values("+token_id+",'"+msg+"','"+msg_id+"')");
			ResultSet resultSet1=MysqlHelper.getInstance().executeQuery("select id from msg where msg_id='"+msg_id+"'");
			resultSet1.next();
			int msg_id_int = resultSet1.getInt("id");
			ResultSet resultSet2 = MysqlHelper.getInstance().executeQuery("select id from "+DbCst.TB_NAME_PUSH_TOKEN+" order by id desc limit 500");
			ArrayList<Integer> tokens = new ArrayList<Integer>();
			while(resultSet2.next()) {
				int token_id2 = resultSet2.getInt("id");
				tokens.add(token_id2);
			}
			for (Integer integer : tokens) {
				System.out.println("insert into msg_receive(token_id,msg_id) values("+integer+","+msg_id_int+")");
				MysqlHelper.getInstance().execute("insert into msg_receive(token_id,msg_id) values("+integer+","+msg_id_int+")");
			}
		}else {
			throw new Exception("the token is not registered");
		}
	}
	
	
	public static void queryMsg(String token) {
//		select * from msg,push_token,msg_receive where msg_receive.msg_id=msg.id and msg_receive.token_id=push_token.id and push_token.token='Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X' and state=0;
//		ResultSet resultSet  = MysqlHelper.getInstance().executeQuery("select * from msg,push_token,msg_receive where msg_receive.msg_id=msg.id and msg_receive.token_id=push_token.id and push_token.token='"+token+"' and state=0;")
	
	}

	public static String queryTokens(String token) throws ClassNotFoundException, SQLException {
		StringBuilder sb  =new StringBuilder();
		ResultSet resultSet = MysqlHelper.getInstance()
				.executeQuery("select token from " + DbCst.TB_NAME_PUSH_TOKEN + " where token!= '" + token + "'");
		boolean isFirst = true;
		while(resultSet.next()) {
			if(isFirst) {
				isFirst = false;
			}else {
				sb.append(',');
			}
			sb.append(resultSet.getString("token"));
		}
		return sb.toString();
	}

	public static BaseHttp<Void> sendUniMsg(Custom custom, String device_tokens) {
		String body = createUnicastMsg(custom, device_tokens);
		System.out.println(body);
		String sign = createSign(body);
		System.out.println(sign);
		BaseHttp<Void> bh = new BaseHttp<>();
		try {
			Response<String> response = RetrofitUtils.getUpRetrofit()
					.create(HttpApi.class)
					.sendMsg(sign, RequestBody.create(MediaType.get("application/json; charset=utf-8"), body))
					.execute();

			int code = response.code();
			String msg = code == HttpCst.CODE_200 ? response.body() : response.errorBody().string();
			System.out.println("msg: "+msg);
			System.out.println("code: "+code);
			bh.setCode(code);
			bh.setMsg(msg);
		} catch (IOException e) {
			e.printStackTrace();
			bh.setCode(HttpCst.CODE_500);
			bh.setMsg(e.toString());
		}
		return bh;
	}

	public static String createUnicastMsg(Custom custom, String device_tokens) {
		UniInfo info = new UniInfo();
		info.setDevice_tokens(device_tokens);
		info.setAppkey(APP_KEY);
		info.setType("listcast");
		long currentMs = System.currentTimeMillis();
		long expiredMs = currentMs + TimeUtils.MS_NUM_DAY * 3;
		info.setTimestamp(currentMs + "");
		 info.setDescription("des");
		Body<Custom> customBody = new Body<>();
		customBody.setCustom(custom);
		PayLoad payLoad = new PayLoad();
		payLoad.setBody(customBody);
		info.setPayload(payLoad);

		Policy policy = new Policy();
		policy.setExpire_time(TimeUtils.SDF_DATETIME_DEFAULT.format(expiredMs));
		info.setPolicy(policy);
		return new Gson().toJson(info);
	}

	public static boolean addUpushTokenIfNotExist(String token) throws SQLException, ClassNotFoundException {
		ResultSet resultSet = MysqlHelper.getInstance()
				.executeQuery("select id from push_token where token = '" + token + "'");
		if (!resultSet.next()) {
			MysqlHelper.getInstance().execute("insert into push_token(token)values('" + token + "')");
		}
		return true;
	}

	// public static void test() {
	// try {
	// Response<String> response =
	// RetrofitUtils.getShowRetrofit().create(HttpApi.class).showAll("mzs",
	// "123456")
	// .execute();
	//
	// System.out.println("isSuccessful : " + response.isSuccessful());
	// System.out.println("code : " + response.code());
	// System.out.println("message : " + response.message());
	// System.out.println("body : " + response.body());
	// System.out.println("errorbody : " + response.errorBody().string());
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// public static BaseHttp<Void> sendBroadcast(Custom custom) {
	// BaseHttp<Void> bh = new BaseHttp<>();
	// String body = createBroadcastMsg(custom);
	// System.out.println(body);
	// String sign = createSign(body);
	// System.out.println(sign);
	// try {
	// Response<String> response =
	// RetrofitUtils.getUpRetrofit().create(HttpApi.class)
	// .sendMsg2(sign, RequestBody.create(MediaType.get("application/json;
	// charset=utf-8"), body))
	// .execute();
	// int code= response.code();
	// String msg = code ==
	// HttpConst.CODE_200?HttpConst.MSG_200:response.errorBody().string();
	// bh.setCode(response.code());
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// bh.setCode(HttpConst.CODE_500);
	// bh.setMsg(e.toString());
	// }
	// return bh;
	//
	// }

	public static String createSign(String body) {
		String src = "POST" + UPUSH_SEND_URL + body + MASTER_SECRET;
		try {
			return EncryptUtils.md5(src).toLowerCase();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// public static String createBroadcastMsg(Custom custom) {
	// SendInfo sendInfo = new SendInfo();
	// sendInfo.setType("broadcast");
	// long currentMs = System.currentTimeMillis();
	// long expiredMs = currentMs + TimeUtils.MS_NUM_DAY * 3;
	// sendInfo.setTimestamp(currentMs + "");
	// sendInfo.setDescription("des");
	// Body<Custom> customBody = new Body<>();
	// customBody.setCustom(custom);
	// PayLoad payLoad = new PayLoad();
	// payLoad.setBody(customBody);
	// sendInfo.setPayload(payLoad);
	//
	// Policy policy = new Policy();
	// policy.setExpire_time(TimeUtils.SDF_DATETIME_DEFAULT.format(expiredMs));
	// sendInfo.setPolicy(policy);
	// return new Gson().toJson(sendInfo);
	// }

	// public static void sendBroadcast() {
	// Custom custom = new Custom();
	// custom.setDt(System.currentTimeMillis());
	// custom.setFrom("maozs");
	// custom.setMsg("1704");
	// String body = createBroadcastMsg(custom);
	//// String body = createPushJson();
	// System.out.println(body);
	// String sign = createSign(body);
	// try {
	// String rt = RetrofitUtils.getUpRetrofit()
	// .create(HttpApi.class)
	// .sendMsg2(sign, RequestBody.create(MediaType.get("application/json;
	// charset=utf-8"), body))
	// .execute().toString();
	// System.out.println(rt);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	// public static void asyn() {
	// System.out.println("start");
	//
	//// String json =
	// "{\"appkey\":\"5dfb0dfbcb23d21f35000057\",\"description\":\"des\",\"filter\":{\"where\":{\"and\":[{\"tag\":\"tag2\"}]}},\"payload\":{\"body\":{\"custom\":{\"dt\":1577433094360,\"from\":\"mzs\",\"msg\":\"hello\"}},\"display_type\":\"message\"},\"timestamp\":\"1577433094359\",\"type\":\"groupcast\"}";
	// String json = createPushJson();//new
	// Gson().toJson(createPushJson())//"{\"appkey\":\"5dfb0dfbcb23d21f35000057\",\"description\":\"des\",\"filter\":{\"where\":{\"and\":[{\"tag\":\"tag2\"}]}},\"payload\":{\"body\":{\"custom\":{\"dt\":1577436042530,\"from\":\"mzs\",\"msg\":\"hello\"}},\"display_type\":\"message\"},\"timestamp\":\"1577436042530\",\"type\":\"groupcast\"}";//"{\"appkey\":\"5dfb0dfbcb23d21f35000057\",\"description\":\"des\",\"filter\":{\"where\":{\"and\":[{\"tag\":\"tag2\"}]}},\"payload\":{\"body\":{\"custom\":{\"dt\":1577435286662,\"from\":\"mzs\",\"msg\":\"hello\"}},\"display_type\":\"message\"},\"timestamp\":\"1577435286662\",\"type\":\"groupcast\"}";//"{\"appkey\":\"5dfb0dfbcb23d21f35000057\",\"description\":\"des\",\"filter\":{\"where\":{\"and\":[{\"tag\":\"tag2\"}]}},\"payload\":{\"body\":{\"custom\":{\"dt\":1577433094360,\"from\":\"mzs\",\"msg\":\"hello\"}},\"display_type\":\"message\"},\"timestamp\":\"1577433094359\",\"type\":\"groupcast\"}";
	// try {
	//// System.out.println(createSign(json).toLowerCase());
	// String body = RetrofitUtils.getUpRetrofit().create(HttpApi.class)
	// .sendMsg2(createSign(json).toLowerCase(),
	// RequestBody.create(MediaType.get("application/json; charset=utf-8"), json))
	// .execute().body();
	// System.out.println(body);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// System.out.println("end");
	// }
	//
	// public static void sync() {
	// System.out.println("start");
	//
	//// String json =
	// "{\"appkey\":\"5dfb0dfbcb23d21f35000057\",\"description\":\"des\",\"filter\":{\"where\":{\"and\":[{\"tag\":\"tag2\"}]}},\"payload\":{\"body\":{\"custom\":{\"dt\":1577433094360,\"from\":\"mzs\",\"msg\":\"hello\"}},\"display_type\":\"message\"},\"timestamp\":\"1577433094359\",\"type\":\"groupcast\"}";
	// String json = createPushJson();//new
	// Gson().toJson(createPushJson())//"{\"appkey\":\"5dfb0dfbcb23d21f35000057\",\"description\":\"des\",\"filter\":{\"where\":{\"and\":[{\"tag\":\"tag2\"}]}},\"payload\":{\"body\":{\"custom\":{\"dt\":1577436042530,\"from\":\"mzs\",\"msg\":\"hello\"}},\"display_type\":\"message\"},\"timestamp\":\"1577436042530\",\"type\":\"groupcast\"}";//"{\"appkey\":\"5dfb0dfbcb23d21f35000057\",\"description\":\"des\",\"filter\":{\"where\":{\"and\":[{\"tag\":\"tag2\"}]}},\"payload\":{\"body\":{\"custom\":{\"dt\":1577435286662,\"from\":\"mzs\",\"msg\":\"hello\"}},\"display_type\":\"message\"},\"timestamp\":\"1577435286662\",\"type\":\"groupcast\"}";//"{\"appkey\":\"5dfb0dfbcb23d21f35000057\",\"description\":\"des\",\"filter\":{\"where\":{\"and\":[{\"tag\":\"tag2\"}]}},\"payload\":{\"body\":{\"custom\":{\"dt\":1577433094360,\"from\":\"mzs\",\"msg\":\"hello\"}},\"display_type\":\"message\"},\"timestamp\":\"1577433094359\",\"type\":\"groupcast\"}";
	//// try {
	// System.out.println(createSign(json).toLowerCase());
	// RetrofitUtils.getUpRetrofit().create(HttpApi.class)
	// .sendMsg2(createSign(json).toLowerCase(),
	// RequestBody.create(MediaType.get("application/json; charset=utf-8"), json))
	//// .execute().body();
	// .enqueue(new Callback<String>() {
	// @Override
	// public void onResponse(Call<String> call, Response<String> response) {
	// System.out.println(response.toString());
	// }
	//
	// @Override
	// public void onFailure(Call<String> call, Throwable t) {
	// System.out.println(t.toString());
	// }
	// });
	//// System.out.println(body);
	//// } catch (IOException e) {
	//// e.printStackTrace();
	//// }
	// System.out.println("end");
	// }

	// public static String createPushJson() {
	// SendInfo sendInfo = new SendInfo();
	// sendInfo.setTimestamp(System.currentTimeMillis() + "");
	// sendInfo.setDescription("des");
	//
	// sendInfo.setFilter(Filter.newFilter(Filter.TYPE_AND, "tag", "tag2"));
	//
	// Custom custom = new Custom();
	// custom.setDt(System.currentTimeMillis());
	// custom.setFrom("maozs");
	// custom.setMsg("1704");
	// Body<Custom> customBody = new Body<>();
	// customBody.setCustom(custom);
	// PayLoad payLoad = new PayLoad();
	// payLoad.setBody(customBody);
	// sendInfo.setPayload(payLoad);
	// return new Gson().toJson(sendInfo);
	// }
	
//{"device_tokens":"Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X","appkey":"5e0ab9fb4ca35742b600066c","timestamp":"1577780637840","type":"unicast","payload":{"display_type":"message","body":{"custom":{"from":"哈哈","dt":1577780637578,"msg":"1704"}}},"policy":{"expire_time":"2020-01-03 16:23:57"}}
//{"device_tokens":"Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X","appkey":"5e0ab9fb4ca35742b600066c","timestamp":"1577780703207","type":"unicast","payload":{"display_type":"message","body":{"custom":{"from":"哈哈","dt":1577780702789,"msg":"1704"}}},"policy":{"expire_time":"2020-01-03 16:25:03"}}
//8c6667d335df7d5b9a12333a1af1d799
}
