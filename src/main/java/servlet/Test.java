package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.I;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Gson gson = new Gson();
		I i  = new I();
		i.setAge(100);
		i.setName("mzs");
		String json = gson.toJson(i);
		System.out.println(json);
		response.getWriter()
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json)
		.append("\n\r")
		.append(json);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
