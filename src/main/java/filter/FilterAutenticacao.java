package filter;

import connection.SingleConnectionBanco;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {

    private static Connection connection;

    public void init(FilterConfig config) throws ServletException {
        connection = SingleConnectionBanco.getConnection();
    }

    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
       try {
           HttpServletRequest req = (HttpServletRequest) request;
           HttpSession session = req.getSession();
           String usuarioLogado = session.getAttribute("usuario").toString();
           String urlParaAutenticar = req.getServletPath(); /*Url que está sendo acessada*/

           /*Validar se está logado ou não*/
           if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
               RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
               request.setAttribute("msg", "Por favor realize o login!");
               redireciona.forward(request, response);
           } else {
               chain.doFilter(request, response);
           }
           connection.commit();
       } catch (Exception e) {
           e.printStackTrace();
           try {
               connection.rollback();
           } catch (SQLException e1) {
               e.printStackTrace();
           }
       }
    }
}
