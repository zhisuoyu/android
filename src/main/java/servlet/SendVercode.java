package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.SignUtils;
import bean.http.BaseHttp;
import cst.HttpCst;
import utils.CheckUtils;
import utils.Lg;
import utils.ServletUtils;

/**
 * Servlet implementation class SendVercode
 */
public class SendVercode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendVercode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtils.setServletUTF8(request,response);
		String email = request.getParameter("email");
		BaseHttp<Void> bh = null;
		if(!CheckUtils.notNull(email)) {
			bh  = new BaseHttp<>(HttpCst.CODE_500,"缺少参数");
		}else {
		Lg.i("email:"+email);
		 bh = SignUtils.handleVercode(email);
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
