package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.db.SysPic;
import bean.http.BaseHttp;
import cst.HttpCst;
import tools.MysqlHelper;
import utils.Lg;
import utils.ServletUtils;

/**
 * Servlet implementation class QuerySysPic
 */
public class QuerySysPic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private String result;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuerySysPic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		ServletUtils.setServletUTF8(request, response);
		if(result==null) {
			synchronized (this) {
				if(result==null) {
						System.out.println("query sysPic");
					BaseHttp<List<SysPic>> bh = new BaseHttp<List<SysPic>>();
					try {
						ResultSet resultSet = MysqlHelper.getInstance().executeQuery("select id,name from pic where type=0");
						List<SysPic> list = new ArrayList<>();
						while(resultSet.next()) {
							list.add(new SysPic(resultSet.getInt("id"),resultSet.getString("name")));
						}
						bh.setCode(HttpCst.CODE_200);
						bh.setMsg(HttpCst.MSG_200);
						bh.setData(list);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						bh.setCode(HttpCst.CODE_500);
						bh.setMsg(HttpCst.MSG_500+":"+e);
					}
					result = AppContext.getInstance().getGson().toJson(bh);
				}
			}
		}
		
		response.getWriter().append(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
