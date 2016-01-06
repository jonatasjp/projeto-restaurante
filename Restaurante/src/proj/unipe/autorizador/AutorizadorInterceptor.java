package proj.unipe.autorizador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		String uri = request.getRequestURI();
		if (uri.endsWith("/") || uri.endsWith("/telaLogin") || uri.endsWith("/cliente/cadastro") || uri.endsWith("/cliente/cadastrar") || uri.endsWith("/cliente/confirmarcadastro") || uri.endsWith("/login") || uri.contains("WebContent/css") ||uri.contains("WebContent/fonts") || uri.contains("WebContent/js")) {
			return true;
		}

		if (request.getSession().getAttribute("usuario") != null) {
			return true;
		}

		response.sendRedirect("http://localhost:8080/Restaurante/");
		return false;
	}

}
