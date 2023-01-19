package servlets;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(name = "ServletUsuarioController", value = "/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {

    private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

    public ServletUsuarioController() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String acao = request.getParameter("acao");

            if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

                String idUser = request.getParameter("id");
                daoUsuarioRepository.deletarUser(idUser);
                List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioListAll();
                request.setAttribute("modelLogins", modelLogins);
                request.setAttribute("msg", "Excluído com sucesso!");
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

            } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {

                String idUser = request.getParameter("id");
                daoUsuarioRepository.deletarUser(idUser);
                response.getWriter().write("Excluído com sucesso Ajax!");

            } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

                String nomeBusca = request.getParameter("nomeBusca");
                List<ModelLogin> dadosJsonUser =  daoUsuarioRepository.consultarUsuarioList(nomeBusca);
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(dadosJsonUser);
                response.getWriter().write(json);

            } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")){

                String id = request.getParameter("id");
                ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(id);

                List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioListAll();
                request.setAttribute("modelLogins", modelLogins);

                request.setAttribute("msg", "Usuário em edição");
                request.setAttribute("usuario", modelLogin);
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

            } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {

                List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioListAll();
                request.setAttribute("msg", "Usuários carregados");
                request.setAttribute("modelLogins", modelLogins);
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

            } else {
                List<ModelLogin> modelLogins = daoUsuarioRepository.consultarUsuarioListAll();
                request.setAttribute("modelLogins", modelLogins);
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
            request.setAttribute("msg", e.getMessage());
            redirecionar.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String msg = "Operação realizada com sucesso!";

            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");

            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
            modelLogin.setNome(nome);
            modelLogin.setEmail(email);
            modelLogin.setLogin(login);
            modelLogin.setSenha(senha);

            if(daoUsuarioRepository.validaLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
                msg = "Já existe usuário com o mesmo login, informe outro login!";
            } else {
                modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
            }

            RequestDispatcher redireciona = request.getRequestDispatcher("principal/usuario.jsp");
            request.setAttribute("msg", msg);
            request.setAttribute("usuario", modelLogin);
            redireciona.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
            request.setAttribute("msg", e.getMessage());
            redirecionar.forward(request, response);
        }
    }
}
