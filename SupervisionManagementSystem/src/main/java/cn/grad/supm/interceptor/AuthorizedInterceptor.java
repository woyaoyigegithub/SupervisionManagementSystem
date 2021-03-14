package cn.grad.supm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.grad.supm.domain.Staff;

public class AuthorizedInterceptor implements HandlerInterceptor {

	private static final String[] IGNORE_URI= {"/loginForm","/login","/404.html"};
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String servletPath=request.getServletPath();
		for(String ignoreUri:IGNORE_URI) {
			if(servletPath.equals(ignoreUri)) {
				return true;
			}
		}
		HttpSession session=request.getSession();
		Staff staff=(Staff) session.getAttribute("staff");
		if(staff==null) {
			request.setAttribute("errorMessage", "您还未登录！");
			request.getRequestDispatcher("/loginForm").forward(request, response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {}
	

}
