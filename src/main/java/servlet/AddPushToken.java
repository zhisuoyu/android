package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.UpushUtils;
import bean.http.BaseHttp;
import cst.HttpCst;
import utils.CheckUtils;
import utils.Lg;
import utils.ServletUtils;

/**
 * Servlet implementation class AddPushToken
 */
public class AddPushToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPushToken() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtils.setServletUTF8(request, response);
		String token  = request.getParameter("token");
		BaseHttp<Void> bh = null;
		if(!CheckUtils.notNull(token)) {
			bh  = new BaseHttp<>(HttpCst.CODE_400, "缺少参数");
		}else {
			try {
				UpushUtils.addUpushTokenIfNotExist(token);
				bh = new BaseHttp<>(HttpCst.CODE_200,HttpCst.MSG_200);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				bh = new BaseHttp<>(HttpCst.CODE_500,HttpCst.MSG_500);
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
