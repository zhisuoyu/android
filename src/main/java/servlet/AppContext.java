package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

import utils.BgTaskHelper;

/**
 * Servlet implementation class AppContext
 */
public class AppContext extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AppContext Instance;
	private Gson gson = new Gson();
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("AppContext init");
		Instance = this;
//		BgTaskHelper.getInstance().start();
	}
 
	public static AppContext getInstance() {
		return Instance;
	}
	
	public Gson getGson() {
		return gson;
	}
	

}
