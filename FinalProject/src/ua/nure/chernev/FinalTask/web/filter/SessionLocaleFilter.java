package ua.nure.chernev.FinalTask.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;



/**
 * Session locale filter.
 * 
 */
public class SessionLocaleFilter implements Filter {
	
	private static final Logger LOG = Logger.getLogger(SessionLocaleFilter.class);
	
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
      throws IOException, ServletException {
 
    	LOG.debug("Filter starts");
    	
        HttpServletRequest req = (HttpServletRequest) request;
        
        if (req.getParameter("sessionLocale") != null) {
            req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
            LOG.debug("Locale have been changed");	
        }
        
        LOG.debug("Filter finished");	
        
        chain.doFilter(request, response);
    }
    public void destroy() {
    	LOG.debug("Filter destruction starts");
		// no op
		LOG.debug("Filter destruction finished");
    }
    public void init(FilterConfig arg0) throws ServletException {}
}
