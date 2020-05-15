package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.SignUtils;
import bean.SignInData;
import bean.http.BaseHttp;
import cst.HttpCst;
import utils.CheckUtils;
import utils.Lg;
import utils.ServletUtils;

/**
 * Servlet implementation class SignIn
 */
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtils.setServletUTF8(request, response);
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		BaseHttp<SignInData> bh = null;
		if (!CheckUtils.notNull(email, pwd)) {
			bh = new BaseHttp<>(HttpCst.CODE_500, "缺少参数");
		} else {
			Lg.i("email:" + email);
			bh = SignUtils.signIn(email, pwd);
		}
		String json = AppContext.getInstance().getGson().toJson(bh);
		Lg.i(json);
		response.getWriter().append(json);

		// BaseHttp<String> bh = new BaseHttp<>(HttpConst.CODE_DEFAULT,
		// HttpConst.MSG_DEFAULT, null);
		// int rt = -1;
		// try {
		// rt = ActionUtils.signIn(name, pwd);
		// switch (rt) {
		// case ActionUtils.SUCCESS:
		// bh.setCode(HttpConst.CODE_SUCCESS);
		// bh.setMsg(HttpConst.MSG_SUCCESS);
		// break;
		// case ActionUtils.NOT_EXIST:
		// bh.setCode(HttpConst.CODE_FAILURE);
		// bh.setMsg("用户名不存在");
		// break;
		// case ActionUtils.ERROR_PWD:
		// bh.setCode(HttpConst.CODE_DEFAULT);
		// bh.setMsg("用户名或密码错误");
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
		doGet(request, response);
	}

}
