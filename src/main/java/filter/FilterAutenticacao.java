package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String usuarioLogado = session.getAttribute("usuario").toString();
        String urlParaAutenticar = req.getServletPath(); /*Url que está sendo acessada*/

        /*Validar se está logado ou não*/
        if(usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
            RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
            request.setAttribute("msg", "Por favor realize o login!");
            redireciona.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
