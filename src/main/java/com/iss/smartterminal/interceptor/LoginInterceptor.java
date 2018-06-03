package com.iss.smartterminal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();

		String[] allowUrls = new String[] { "/doctor/login", "/doctor/new", "/doctor/register", "/js", "/css", "/img", "/record/add", "patient/exist" };
		String url = request.getRequestURL().toString();
		System.out.println(url);
		String[] paths = url.split("/");
		if ("smartTerminalApp".equals(paths[paths.length - 1]) || "smartTerminalApp".equals(paths[paths.length - 2])) {
			return true;
		}
		for (String strUrl : allowUrls) {
			if (url.contains(strUrl)) {
				return true;
			}
		}

		UserToken user = (UserToken) session.getAttribute("userToken");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/doctor/login");
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
