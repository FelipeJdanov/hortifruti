package com.hortifruti.application;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.UserAgentType;
import net.sf.uadetector.service.UADetectorServiceFactory;
 
/**
 * Esta classe cria um cache do User-Agent do request 
 * a fim de n�o prejudicar o desempenho da aplica��o.
 * 
 * A chamada do User-Agent � utilizada para identificar se a requisi��o 
 * foi efetuada atrav�s de um browser ou um rob� por exemplo. 
 * N�o � o melhor m�todo, mas funciona em casos simples.
 *
 */
public final class UserAgent implements UserAgentStringParser {
	
	static final Logger LOGGER = LogManager.getLogger(UserAgent.class.getName());
 
	private final UserAgentStringParser parser = UADetectorServiceFactory.getCachingAndUpdatingParser();
	private final Map<String, ReadableUserAgent> cache = new HashMap<String, ReadableUserAgent>();
	private static UserAgent cachedUserAgentStringParser = new UserAgent();
	
	@Override
	public String getDataVersion() {
		return parser.getDataVersion();
	}
 
	@Override
	public ReadableUserAgent parse(final String userAgentString) {
		ReadableUserAgent result = cache.get(userAgentString);
		if (result == null) {
			result = parser.parse(userAgentString);
			cache.put(userAgentString, result);
		}
		return result;
	}
 
	@Override
	public void shutdown() {
		parser.shutdown();
	}
	
	
	/**
	 * Identifica a origem da requisi��o.
	 * � permitido apenas requisi��es feitas atrav�s de bowsers.
	 * 
	 * @return Retorna true caso a resquisi��o teha sido atrav�s de Bowser, do contr�rio retorna false.
	 */
	public static boolean isUsuarioValido(){
		
		final HttpServletRequest request =(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		ReadableUserAgent agent = cachedUserAgentStringParser.parse(request.getHeader("user-agent"));
		
		if(UserAgentType.BROWSER != agent.getType() && UserAgentType.MOBILE_BROWSER != agent.getType()){
			LOGGER.info("Nome do navegador: "+agent.getName());
			LOGGER.info("Sistema operante: "+agent.getOperatingSystem().getName());
			LOGGER.info("Origem da solicita��o: "+agent.getType());
			
			return false;
		}
		return true;
	}
 
}