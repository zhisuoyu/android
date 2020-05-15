package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.http.BaseHttp;
import cst.FileCst;
import cst.HttpCst;
import tools.MysqlHelper;
import utils.CheckUtils;
import utils.FilePathUtils;
import utils.IoUtils;
import utils.ServletUtils;
import utils.UUID22;

/**
 * Servlet implementation class UploadPic
 */
@MultipartConfig
public class UploadPic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadPic() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletUtils.setServletUTF8(request, response);
		Part part = request.getPart("file");
		System.out.println("part:"+part);
		String userId = request.getParameter("userId");
		System.out.println("userId:"+userId);
		BaseHttp<String> bh = null;
		if (userId == null || part == null) {
			bh = new BaseHttp<>(HttpCst.CODE_500, "缺少参数");
		} else {
			try {
				String picName = UUID22.random().toString() + ".jpg";
				String picUrl = FileCst.DIR_PIC_USER + "/" + picName;
				OutputStream os = new FileOutputStream(new File(FilePathUtils.getUserPicDir(request), picName));
				IoUtils.is2os(part.getInputStream(), os);
				MysqlHelper.getInstance().execute("update user set pic_url='" + picUrl + "' where id=" + userId);
				bh = new BaseHttp<String>(HttpCst.CODE_200, HttpCst.MSG_200);
				bh.setData(picUrl);
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
				bh = new BaseHttp<String>(HttpCst.CODE_400, e.toString());
			}
		}

		String json = AppContext.getInstance().getGson().toJson(bh);
		response.getWriter().append(json);
	}

}
