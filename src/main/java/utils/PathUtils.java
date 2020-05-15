//package utils;
//
//import java.io.File;
//
//import javax.servlet.http.HttpServletRequest;
//
//import servlet.AppContext;
//
//public class PathUtils {
//	
//	public static final String PATH_ROOT_READ = "./";
//	public static final String PATH_ROOT_WRITE = AppContext.getInstance().getServletContext().getRealPath("/");
//
//	public static final String PATH_IMG = "img";
//	public static final String PATH_IMG_PIC_SYSTEM = PATH_IMG + "/pic_system/";
//	public static final String PATH_IMG_PIC_USER = PATH_IMG + "/pic_user/";
//
//	public static File getDir(String dirPath) {
//		File dir = new File(dirPath);
//		if(!dir.isDirectory()) {
//			dir.mkdirs();
//		}
//		return dir;
//	}
//
////	public static File getUserPicDir() {
////		return getDir(dirPath)
////	}
//	
//
////	private void test(File file) {
////		file.get
////	}
//	
////	private PathUtils() {
////		throw new 
////	}
////
////	public static void init(HttpServletRequest request) {
////		writePath = request.getServletContext().getRealPath("/");
////	}
//
////	public static String getReadPath() {
////		return readPath;
////	}
////
////	public static String getWritePath() {
////		return writePath;
////	}
//
//}
