package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.http.BaseHttp;
import cst.HttpCst;
import tools.MysqlHelper;
import utils.CheckUtils;
import utils.Lg;
import utils.ServletUtils;

/**
 * Servlet implementation class UpdatePic
 */
public class UpdatePic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtils.setServletUTF8(request, response);
		String userId = request.getParameter("userId");
		String picUrl = request.getParameter("picUrl");
		BaseHttp<Void> bh = null;
		if (!CheckUtils.notNull(userId,picUrl)) {
			bh = new BaseHttp<>(HttpCst.CODE_500, "缺少参数");
		}else {
			try {
				MysqlHelper.getInstance().execute("update user set pic_url='"+picUrl+"' where id="+userId);
				bh= new BaseHttp<Void>(HttpCst.CODE_200,HttpCst.MSG_200);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				bh= new BaseHttp<Void>(HttpCst.CODE_400,e.toString());
			}
		}
		String json = AppContext.getInstance().getGson().toJson(bh);
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
