package br.com.ricardotulio.mikrotikadmin.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		
		if (uri.equals(contextPath + "/login") || uri.startsWith(contextPath + "/assets") || request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}
		
		response.sendRedirect(contextPath + "/login");
		return false;
	}

}
