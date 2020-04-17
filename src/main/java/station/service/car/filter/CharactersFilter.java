package station.service.car.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharactersFilter", urlPatterns = "/")

public class CharactersFilter implements Filter
{
    private FilterConfig filterConfig=null;

    public CharactersFilter()
    {
        System.out.println("Request response encoder Filter object has been created");
    }

    public void init(FilterConfig filterConfig)
    {
        this.filterConfig=filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);

        response.setContentType("text/html; charset=UTF-8");
    }

    public void destroy() {
        this.filterConfig=null;
    }
}
