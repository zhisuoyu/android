package utils;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import cst.FileCst;

public class FilePathUtils {
	
	private static File userPicDir;
	
	public static File getUserPicDir(HttpServletRequest request) {
		if(userPicDir==null) {
			synchronized (FilePathUtils.class) {
				if(userPicDir==null) {
					userPicDir = new File(ServletUtils.getRealpath(request, FileCst.DIR_PIC_USER));
				}
			}
		}
    	if(!userPicDir.isDirectory()) {
    		System.out.println("mkdirs:"+userPicDir.getAbsolutePath());
    		userPicDir.mkdirs();
    	}
    	return userPicDir;
    }

}
