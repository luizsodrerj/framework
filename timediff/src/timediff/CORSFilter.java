package timediff;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

	private static final String OPTIONS = "OPTIONS";
	private static final String POST 	= "POST";
    private static final String GET  	= "GET";

    
    
	/**
     * Default constructor.
     */
    public CORSFilter() {
    }
 
    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }
 
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest request = (HttpServletRequest) servletRequest;
 
        // Authorize (allow) all domains to consume the content
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String method = request.getMethod(); 
        
        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (GET.equals(method) || POST.equals(method) || OPTIONS.equals(method) ) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
 
        // pass the request along the filter chain
        chain.doFilter(request, servletResponse);
    }
 
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }
 
}
