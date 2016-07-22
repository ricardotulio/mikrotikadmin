package br.com.ricardotulio.mikrotikadmin.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		System.out.println(request.getServletPath());

		if (request.getServletPath().equals("/login") || request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}

		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}

}
