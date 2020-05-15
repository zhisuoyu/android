package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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
 * Servlet implementation class SignUp
 */
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ServletUtils.setServletUTF8(request, response);
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String vercode = request.getParameter("vercode");
		String pwd = request.getParameter("pwd");

		BaseHttp<Void> bh = null;
		if (!CheckUtils.notNull(name, email, vercode, pwd)) {
			bh = new BaseHttp<>(HttpCst.CODE_500, "缺少参数");
		} else {
			Lg.i("email:" + email);
			bh = SignUtils.signUp(name, email, vercode, pwd);
		}
		String json = AppContext.getInstance().getGson().toJson(bh);
		Lg.i(json);
		response.getWriter().append(json);

		// System.out.println(email + ":" + pwd);
		// BaseHttp<String> bh = new BaseHttp<>(HttpConst.CODE_DEFAULT,
		// HttpConst.MSG_DEFAULT, null);
		// int rt = -1;
		// try {
		// rt = ActionUtils.signUp(email, pwd);
		// switch (rt) {
		// case ActionUtils.SUCCESS:
		// bh.setCode(HttpConst.CODE_SUCCESS);
		// bh.setMsg(HttpConst.MSG_SUCCESS);
		// break;
		// case ActionUtils.ALREADY_EXIST:
		// bh.setCode(HttpConst.CODE_FAILURE);
		// bh.setMsg("用户名已存在");
		// break;
		// }
		// } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e)
		// {
		// e.printStackTrace();
		// bh.setCode(HttpConst.CODE_FAILURE);
		// bh.setMsg(e.toString());
		// Lg.e(e);
		// }
		// String json = AppContext.getInstance().getGson().toJson(bh);
		// Lg.i(json);
		// response.getWriter().append(json);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
