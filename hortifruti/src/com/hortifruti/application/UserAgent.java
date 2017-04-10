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
 * a fim de não prejudicar o desempenho da aplicação.
 * 
 * A chamada do User-Agent é utilizada para identificar se a requisição 
 * foi efetuada através de um browser ou um robô por exemplo. 
 * Não é o melhor método, mas funciona em casos simples.
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
	 * Identifica a origem da requisição.
	 * É permitido apenas requisições feitas através de bowsers.
	 * 
	 * @return Retorna true caso a resquisição teha sido através de Bowser, do contrário retorna false.
	 */
	public static boolean isUsuarioValido(){
		
		final HttpServletRequest request =(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		ReadableUserAgent agent = cachedUserAgentStringParser.parse(request.getHeader("user-agent"));
		
		if(UserAgentType.BROWSER != agent.getType() && UserAgentType.MOBILE_BROWSER != agent.getType()){
			LOGGER.info("Nome do navegador: "+agent.getName());
			LOGGER.info("Sistema operante: "+agent.getOperatingSystem().getName());
			LOGGER.info("Origem da solicitação: "+agent.getType());
			
			return false;
		}
		return true;
	}
 
}