import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.core.StandardWrapperFacade;

public class HelloServlet extends HttpServlet{

	private String user;
	
	public HelloServlet() {}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setUser(getServletConfig().getInitParameter("user"));
		
		PrintWriter writer=resp.getWriter();
		writer.append("hello "+user+"!");
		System.out.println("hello1");
		//super.doGet(req, resp);
	}
}
