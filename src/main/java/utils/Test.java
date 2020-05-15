package utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Test {
	public static void main(String[] args) throws SecurityException, IOException {
//		Logger.getGlobal().
//		Logger.getGlobal().info("hello");
//		Logger.getGlobal().info("info");
//		Logger.getGlobal().fine("fine");
		FileHandler fileHandler = new FileHandler("E:/log",100,2,true);
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new SimpleFormatter());
        Logger.getGlobal().addHandler(fileHandler);
        for (int i = 0; i < 100; i++) {
        	Logger.getGlobal().info("hello:"+i);
		}
        

        
//        fileHandler.setFormatter(new Formatter() {//定义一个匿名类
//            @Override
//            public String format(LogRecord record) {
//                return record.getLevel() + ":" + record.getMessage() + "\n";
//            }
//        });
//        logger.addHandler(fileHandler);
//        logger.info("测试");
	}
}


