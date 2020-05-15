package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletUtils {

	
	public static void setServletUTF8(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
	}
	
	
	
	public static String iterateRequest(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		String headers = iterateHeader(request);
		// String attrs = IterateUtils.iterateAttrs(request);
		String params = iterateParams(request);
		String body = iterateBody(request);

		String remoteIp = request.getRemoteAddr();// 获取客户端的ip
		int remotePort = request.getRemotePort();// 获取客户端的端口号
		String serverName = request.getRemoteHost();// 获取远程计算机的名字

		sb.append("headers:" + headers + "\n");
		// sb.append("attrs:" + attrs + "\n");
		sb.append("params:" + params + "\n");
		sb.append("body:" + body + "\n");
		sb.append("remoteIp:" + remoteIp + "\n"); sb.append("remotePort:" + remotePort + "\n");
		sb.append("serverName:" + serverName + "\n");
		return sb.toString();
	}

	public static String iterateHeader(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		Enumeration<String> en = request.getHeaderNames();
		sb.append("(");
		while (en.hasMoreElements()) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(",");
			}
			String name = en.nextElement();
			sb.append(name + " ---> " + request.getHeader(name));
		}
		sb.append("");
		return sb.toString();
	}

	public static String iterateAttrs(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		Enumeration<String> en = request.getAttributeNames();
		sb.append("(");
		while (en.hasMoreElements()) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(",");
			}
			String name = en.nextElement();
			sb.append(name + " ---> " + request.getAttribute(name));
		}
		sb.append(")");
		return sb.toString();
	}

	public static String iterateParams(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		Enumeration<String> en = request.getParameterNames();
		sb.append("(");
		while (en.hasMoreElements()) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(",");
			}
			String name = en.nextElement();
			sb.append(name + " ---> " + request.getParameter(name));
		}
		sb.append(")");
		return sb.toString();
	}

	public static String iterateBody(HttpServletRequest request) {
		String body = null;
		try {
			body =is2str(request.getInputStream(), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}
	
	private static String is2str(InputStream is, String charset) throws IOException {
		if (is == null) {
			return null;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
		StringBuilder stringBuilder = new StringBuilder();
		String curLine;
		while ((curLine = reader.readLine()) != null) {
			stringBuilder.append(curLine);
		}
		return stringBuilder.toString();
	}
	
	
	public static String getRealpath(HttpServletRequest request,String name) {
		return request.getServletContext().getRealPath(name);
	}

}
