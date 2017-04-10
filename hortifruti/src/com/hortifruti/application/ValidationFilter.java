package com.hortifruti.application;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Filtro de validação chamado a cada requisição ao servidor.
 */
@WebFilter("/*")
public class ValidationFilter implements Filter {

    public ValidationFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		/*if (!UserAgent.isUsuarioValido()) {
			throw new BusinessException("Acesso ao sistema inválido!");
		}else{*/
			chain.doFilter(request, response);
		/*}*/
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
