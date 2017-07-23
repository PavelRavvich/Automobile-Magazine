package ru.pravvich.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Author : Pavel Ravvich.
 * Created : 22.07.17.
 * <p>
 * EncodingFilter determines encoding UTF-8.
 */
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
