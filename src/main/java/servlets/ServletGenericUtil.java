package servlets;

import connection.SingleConnectionBanco;
import dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.ModelLogin;

import java.sql.Connection;

public class ServletGenericUtil extends HttpServlet {
    private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

    public Long getUserLogado(HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        String userLogado = (String) session.getAttribute("usuario");
        return daoUsuarioRepository.consultaUsuarioLogado(userLogado).getId();
    }
}
