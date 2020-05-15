package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.UpushUtils;
import bean.chat.Custom;
import bean.http.BaseHttp;
import cst.HttpCst;
import utils.CheckUtils;
import utils.EncryptUtils;
import utils.Lg;
import utils.ServletUtils;

/**
 * Servlet implementation class SendMsg
 */
public class SendMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMsg() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static void t() {
    	try {
    		String body = "{\"device_tokens\":\"Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X\",\"appkey\":\"5e0ab9fb4ca35742b600066c\",\"timestamp\":\"1577782694506\",\"type\":\"unicast\",\"payload\":{\"display_type\":\"message\",\"body\":{\"custom\":{\"dt\":1577782694506,\"msg\":\"ss\"}}}}";
//		String body = ""
    		System.out.println(EncryptUtils.md5(body));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
//    	4AC6E7316001EBC325038AB276F1CDC1

    	p();
//    	t();//2D72BCAF7A05E6809F8686AE9A47A5D3
    	//String body = "{\"device_tokens\":\"Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X\",\"appkey\":\"5e0ab9fb4ca35742b600066c\",\"timestamp\":\"1577782694506\",\"type\":\"unicast\",\"payload\":{\"display_type\":\"message\",\"body\":{\"custom\":{\"dt\":1577782694506,\"msg\":\"你好\"}}}}";
//    	try {
//			UpushUtils.test();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//    	try {
//			UpushUtils.test();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
//    	String body = "{\"device_tokens\":\"Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X\",\"appkey\":\"5e0ab9fb4ca35742b600066c\",\"timestamp\":\"1577782694506\",\"type\":\"unicast\",\"payload\":{\"display_type\":\"message\",\"body\":{\"custom\":{\"dt\":1577782694506,\"msg\":\"你好\"}}}}";
//        System.out.println(UpushUtils.createSign(body));
	}
    

    public static void p() {
    	try {
			System.out.println(EncryptUtils.md5("和"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//    AB20CC42F302ED3011EC4E3AA547933E
//    AB20CC42F302ED3011EC4E3AA547933E
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		p();
//		t();
//		UpushUtils.test();
		ServletUtils.setServletUTF8(request,response);
//
//		Custom custom = new Custom();
//		custom.setDt(System.currentTimeMillis());
//		custom.setFrom("阿斯顿");
//		custom.setMsg("阿斯顿");
////		System.out.println(sendUniMsg(custom, "Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X"));
//		try {
//			System.out.println(UpushUtils.sendUniMsg(custom,UpushUtils.queryTokens("12345678")));
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		String body = "{\"device_tokens\":\"Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X\",\"appkey\":\"5e0ab9fb4ca35742b600066c\",\"timestamp\":\"1577782694506\",\"type\":\"unicast\",\"payload\":{\"display_type\":\"message\",\"body\":{\"custom\":{\"dt\":1577782694506,\"msg\":\"你好\"}}}}";
//        System.out.println(UpushUtils.createSign(body));
		//		UpushUtils.t();
		
		String token  =  "all";//;"AgUeSQJdDYnwiqWOPcdqB3FVBONX_ftzES3158okSD1a";//request.getParameter("token");
		String name = request.getParameter("name");
		String msg = request.getParameter("msg");
		String picUrl = request.getParameter("picUrl");
		BaseHttp<Void> bh = null;
		if(!CheckUtils.notNull(token,name,msg)) {
			bh  = new BaseHttp<>(HttpCst.CODE_400, "缺少参数");
		}else {
			String tokens;
			try {
				tokens = UpushUtils.queryTokens(token);
				Custom custom = new Custom();
				custom.setDtMs(System.currentTimeMillis());
				custom.setFrom(name);
				custom.setMsg(msg);
				custom.setPicUrl(picUrl);
				
//				custom.setFrom(name);
//				custom.setMsg(msg);
				bh = UpushUtils.sendUniMsg(custom,tokens);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				bh = new BaseHttp<>(HttpCst.CODE_500,"query tokens failed : "+e);
			}
			
		}

		String json = AppContext.getInstance().getGson().toJson(bh);
		Lg.i(json);
		response.getWriter().append(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
