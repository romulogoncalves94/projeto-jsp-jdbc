package servlets;

import dao.DAOUsuarioRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ModelLogin;

import java.io.IOException;

@WebServlet(name = "ServletTelefone", value = "/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {

    private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
    public ServletTelefone() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String iduser = request.getParameter("iduser");

        try {

            ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(iduser));
            request.setAttribute("usuario", modelLogin);
            request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
