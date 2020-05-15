package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cst.FileCst;
import utils.IoUtils;
import utils.ServletUtils;
import utils.UUID22;

/**
 * Servlet implementation class uploadIcon
 */
//@WebServlet
@MultipartConfig
public class UploadIcon extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private  File dir ;
    
    private File getDir(HttpServletRequest request) {
    	if(dir ==null) {
    		dir = new File(ServletUtils.getRealpath(request, FileCst.DIR_PIC_USER));
    	}
    	System.out.println(dir.getAbsolutePath());
    	if(!dir.isDirectory()) {
    		System.out.println("mkdirs:"+dir.getAbsolutePath());
    		dir.mkdirs();
    	}
    	return dir;
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadIcon() {
        super();
//    	System.out.println("UploadIcon");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtils.setServletUTF8(request, response);
	    Part part = request.getPart("file");

//	    long fileSize = part.getSize();//文件大小
//	    String inputName = part.getName();//input中的name属性名称
//	    System.out.println("文件大小："+fileSize);
//	    System.out.println("input中的name属性名称："+inputName);

	    //获得上传的文件名称
//	    String header = part.getHeader("Content-Disposition");
	    //返回f字母所在的位置
//	    int idx = header.lastIndexOf("filename=\"");
//	    //filename="xxx.xxxx";xxx.xxx的位置是idx+10~header.length()-1;
//	    String fileName = header.substring(idx+10, header.length()-1);
//	    System.out.println("文件名："+fileName);
	    
	    

	    String picName = UUID22.random().toString()+".jpg";
	    String picUrl = FileCst.DIR_PIC_USER+"/"+picName;
	    
	    OutputStream os = new FileOutputStream(new File(getDir(request),picName));
	    IoUtils.is2os(part.getInputStream(), os);
	    
	    response.getWriter().append("success");
	}

}

// TODO Auto-generated method stub
//int size = request.getParts().size();
//System.out.println("size:"+size);
//doGet(request, response);
// request.setCharacterEncoding("UTF-8");
//    response.setContentType("text/html;UTF-8");
    //接收参数
    //文件描述
//    String filedesc = request.getParameter("filedesc");
//    System.out.println("文件描述："+filedesc);
    //接收文    

//    System.out.println("getInputStream");
//    InputStream is = part.getInputStream();
//    byte[] buffer  = new byte[1024];
//    System.out.println("start");
//    System.out.println(is.read(buffer));
//    System.out.println("end");
    

//    OutputStream os = new FileOutputStream(new File(getDir(request),"test"));
    
    
    

    //获得文件内容
//    InputStream is = part.getInputStream();

    //获得文件上传位置uploadFiles的路径:xxx/xxx/uploadFiles
//    String path = this.getServletContext().getRealPath("/uploadFiles");

    //获得唯一文件名，不重复
//    String uuidFileName = UUIDUtils.getUUIDFilename(fileName);
//    //path是uploadFiles的路径，UploadUtils.getPath(uuidFileName)获取的是文件在uploadFiles里面的路径。
//    String realPath = path + UploadUtils.getPath(uuidFileName);

    //new File(String pathname):通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例
//    File file = new File(realPath);
//    //如果file不存在,在这个路径下创建文件
//    if(!file.exists()) {
//        file.mkdirs();
//    }

    //在realPath下创建名称为uuidFileName的文件。
//    OutputStream os = new FileOutputStream(new File(getDir(request),"test"));
//    byte[] b = new byte[1024];
//    int len = 0;
//    //read:从输入流读取数据的下一个字节。 值字节被返回作为int范围0至255 。 如果没有字节可用，因为已经到达流的末尾，则返回值-1 。
//    while((len = is.read()) != -1) {
//        os.write(b, 0, len);
//    }













//ServletUtils.setServletUTF8(request, response);
//Part part = request.getPart("file");
//
//long fileSize = part.getSize();//文件大小
//String inputName = part.getName();//input中的name属性名称
//System.out.println("文件大小："+fileSize);
//System.out.println("input中的name属性名称："+inputName);
//
////获得上传的文件名称
//String header = part.getHeader("Content-Disposition");
////返回f字母所在的位置
//int idx = header.lastIndexOf("filename=\"");
////filename="xxx.xxxx";xxx.xxx的位置是idx+10~header.length()-1;
//String fileName = header.substring(idx+10, header.length()-1);
//System.out.println("文件名："+fileName);
//
//
//
//OutputStream os = new FileOutputStream(new File(getDir(request),UUID22.random().toString()+".jpg"));
//IoUtils.is2os(part.getInputStream(), os);
//
//response.getWriter().append("success");



