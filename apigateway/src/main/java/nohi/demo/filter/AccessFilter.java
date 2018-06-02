package nohi.demo.filter;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nohi on 2018/5/31.
 */
public class AccessFilter extends ZuulFilter {
	private Logger log = LoggerFactory.getLogger( JsonProperty.Access.class );
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext rc = RequestContext.getCurrentContext();
		HttpServletRequest request = rc.getRequest();
		log.info( "send {} request to {}" , request.getMethod() , request.getRequestURL() );

		Object accessToken = request.getParameter( "accessToken" );
		if ( null == accessToken) {
			log.warn( "accessToken is null" );
			rc.setSendZuulResponse( false );
			rc.setResponseStatusCode( 401 );
			return rc;
		}
		log.info( "access token Ok : {}" ,accessToken);
		return null;
	}
}
