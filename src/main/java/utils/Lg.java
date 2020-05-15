package utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.sound.midi.Soundbank;

import sun.util.logging.LoggingSupport;

/**
 * 日志输出到登陆系统用户所在的根目录的邮政储蓄日志的文件夹下， 并且根据系统日期命名日志文件
 * 
 * @author wang
 * 
 */
public class Lg {

	/** 存放的文件夹 **/
	// private static String file_name = "邮政储蓄日志";

	public static final String LogDirPath = "log";
	public static final SimpleDateFormat FILENAME_SDF = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat RECORD_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
	

	private static String dtFm;
	private static String errorDtFm;
	private static Logger InfoLogger = Logger.getLogger("INFO");
	private static Logger ErrorLogger = Logger.getLogger("ERROR");
	
	private static final SimpleFormatter FORMATTER = new SimpleFormatter() {
		
		public synchronized String format(LogRecord record) {
	        String source;
	        if (record.getSourceClassName() != null) {
	            source = record.getSourceClassName();
	            if (record.getSourceMethodName() != null) {
	               source += " " + record.getSourceMethodName();
	            }
	        } else {
	            source = record.getLoggerName();
	        }
	        if (record.getThrown() != null) {
	            StringWriter sw = new StringWriter();
	            PrintWriter pw = new PrintWriter(sw);
	            pw.println();
	            record.getThrown().printStackTrace(pw);
	            pw.close();
	        }
	        String fm= RECORD_SDF.format(System.currentTimeMillis())+" "
	        		+source+"  "
	        		+Thread.currentThread().getName()
	        		+"("+Thread.currentThread().getId()+")\n"
	        		+record.getMessage()+"\n";
	        
	        return fm;
		};
	};


	private static String getDtFm() {
		return FILENAME_SDF.format(System.currentTimeMillis());
	}

	private static String getLogFilePath(String dtFm) {
		File logDir = new File(LogDirPath);
		if (!logDir.isDirectory()) {
			logDir.mkdirs();
		}

		return LogDirPath + "/" + dtFm;
	}

	public static Logger getInfoLogger() {
		String currentDtfm = getDtFm();
		if (dtFm == null || (!dtFm.equals(currentDtfm))) {
			dtFm = currentDtfm;
			try {
				FileHandler handler = new FileHandler(getLogFilePath(currentDtfm), true);
				handler.setFormatter(FORMATTER);
				InfoLogger.addHandler(handler);
			} catch (SecurityException | IOException e) {
				e.printStackTrace();
				ErrorLogger.log(Level.SEVERE, e.getMessage());
			}
		}
		return InfoLogger;
	}
	
	public static Logger getErrorLogger() {
		String currentDtfm = getDtFm();
		if (errorDtFm == null || (!errorDtFm.equals(currentDtfm))) {
			errorDtFm = currentDtfm;
			try {
				FileHandler handler = new FileHandler(getLogFilePath(currentDtfm)+".err", true);
				handler.setFormatter(FORMATTER);
				ErrorLogger.addHandler(handler);
			} catch (SecurityException | IOException e) {
				e.printStackTrace();
				ErrorLogger.log(Level.SEVERE, e.getMessage());
			}
		}
		return ErrorLogger;
	}
	
	public static void i(String msg) {
		getInfoLogger().info(msg);
	}
	public static void e(Throwable e) {
		getErrorLogger().severe(getThrowableTraceMsg(e));
	}
	
	public static String getThrowableTraceMsg(Throwable e) {
		String msg = e.toString()+"\n";
		StackTraceElement[] traces = e.getStackTrace();
        for (int i = 0; i < traces.length; i++) {
        	msg+="\tat " + traces[i]+"\n";
        }
		return msg;
	}
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 100; i++) {
			i("item:"+i);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				String msg =null;
				msg.getBytes();
			}catch(NullPointerException e) {
				e(e);
			}
		}
		

	}
}


// /**
// * 配置Logger对象输出日志文件路径
// * @param logger
// * @throws SecurityException
// * @throws IOException
// */
// public static void setLogingProperties(Logger logger) throws
// SecurityException, IOException {
// setLogingProperties(logger,Level.ALL);
// }

// /**
// * 配置Logger对象输出日志文件路径
// * @param logger
// * @param level 在日志文件中输出level级别以上的信息
// * @throws SecurityException
// * @throws IOException
// */
// public static void setLogingProperties(Logger logger,Level level) {
// FileHandler fh;
// try {
// fh = new FileHandler(getLogName(),true);
// logger.addHandler(fh);//日志输出文件
// //logger.setLevel(level);
// fh.setFormatter(new SimpleFormatter());//输出格式
// //logger.addHandler(new ConsoleHandler());//输出到控制台
// } catch (SecurityException e) {
// logger.log(Level.SEVERE, "安全性错误", e);
// } catch (IOException e) {
// logger.log(Level.SEVERE,"读取文件日志错误", e);
// }
// }
