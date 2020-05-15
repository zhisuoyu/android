package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;

/**
 * Servlet implementation class Update
 */
public class Update extends HttpServlet {

	public static void list(String path) {
		System.out.println(Arrays.toString(new File(path).list()));
	}

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Update() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		ServletUtils.setServletUTF8(request, response);
//		File userPicDir = PathUtils.getDir(PathUtils.PATH_ROOT_WRITE+PathUtils.PATH_IMG_PIC_USER);
//		File file = new File(userPicDir,"test.txt");
//		FileIoUtils.writeFileFromString(file, "Hello World");
//		
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath())
//		.append("path:"+PathUtils.PATH_ROOT_READ+PathUtils.PATH_IMG_PIC_USER);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		String webappPath = request.getServletContext().getRealPath("/");
//		System.out.println(webappPath);
//		File testDir = new File(webappPath+"test");
//		if(!testDir.isDirectory()) testDir.mkdirs();
//		FileIoUtils.writeFileFromString(new File(testDir,"test.txt"), "Hello World");
//		list(request.getServletContext().getContextPath());
//		list(request.getServletPath());
//		list(request.getServletContext().getRealPath("/"));
//		System.out.println(new File("./").getAbsolutePath());
//		System.out.println(new File("/").getAbsolutePath());
//		System.out.println(list());
//		System.out.println(request.getServletPath());
//		System.out.println(request.getServletContext().getRealPath("/"));
		// TODO Auto-generated method stub
//		UpdateDefaultPic.test();
//		File file = new File("/");
//		System.out.println(file.getAbsolutePath());
//		System.out.println(Arrays.toString(file.list()));
//		String path  = request.getServletContext().getRealPath("/");
//		File picDir = new File(path,"img/pic_default");
//		System.out.println(picDir.exists());
//		UpdateDefaultPic.update(picDir);
//				System.out.println(Arrays.toString(filenames));
//		response.getWriter().append("Served at: ").append(request.getContextPath());
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
