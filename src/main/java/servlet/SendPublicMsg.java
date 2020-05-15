package servlet;

import java.io.IOException;
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
import utils.Lg;
import utils.ServletUtils;

/**
 * Servlet implementation class SendPublicMsg
 */
public class SendPublicMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendPublicMsg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
		/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletUtils.setServletUTF8(request,response);
		String token  =  "Al0qzMZWXs_JpYwabBI-8KyEu2kVrbs4FediPn6FqJ6X";//request.getParameter("token");
		String name = request.getParameter("name");
		String msg = request.getParameter("msg");

		BaseHttp<Void> bh = null;
		if(!CheckUtils.notNull(token,name,msg)) {
			bh  = new BaseHttp<>(HttpCst.CODE_400, "缺少参数");
		}else {
			String tokens;
			try {
				UpushUtils.addToDb(token,msg);
				tokens = UpushUtils.queryTokens(token);
				Custom custom = new Custom();
				custom.setDtMs(System.currentTimeMillis());
				custom.setFrom(name);
				custom.setMsg(msg);
		
//				custom.setFrom(name);
//				custom.setMsg(msg);
				bh = UpushUtils.sendUniMsg(custom,tokens);
			} catch (Exception e) {
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
