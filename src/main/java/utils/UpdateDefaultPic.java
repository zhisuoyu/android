package utils;

import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;

import tools.MysqlHelper;

public class UpdateDefaultPic {
	public static void main(String[] args) {
		File file = new File("D:\\java\\javaee\\workspace\\android\\src\\main\\webapp\\img\\pic_system");
//		System.out.println(Arrays.toString(file.list()));
		update(file);
	}

	public static void update(File picDir) {
		String[] picNames = picDir.list();
		if (picNames.length > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("insert into pic_system(name) values ").append("('" + picNames[0] + "')");
			for (int i = 1; i < picNames.length; i++) {
				sb.append(",").append("('" + picNames[i] + "')");
			}
			System.out.println(sb);
			try {
				 MysqlHelper.getInstance().execute(sb.toString());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("size:"+picNames.length);
//		for(String name:picNames) {
//			
////			File file  =new File(picDir,name);
////			String newName  = UUID22.random().toString();
////			System.out.println("new:"+newName);
////			File newFile = new File(picDir,newName+".jpeg");
////			System.out.println(file.getAbsolutePath());
////			boolean rt = file.renameTo(newFile);
////			System.out.println(rt);
//		}

	}
}
