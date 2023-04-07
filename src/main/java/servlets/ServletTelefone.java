package servlets;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ModelLogin;
import model.ModelTelefone;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletTelefone", value = "/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {

    private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
    private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();
    public ServletTelefone() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");

            if (acao != null && !acao.isEmpty() && acao.equals("excluir")) {
                String idFone = request.getParameter("id");
                daoTelefoneRepository.deleteFone(Long.parseLong(idFone));
                String userPai = request.getParameter("userpai");
                ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(userPai));
                List<ModelTelefone> modelTelefones = daoTelefoneRepository.listaFone(modelLogin.getId());

                request.setAttribute("msg", "Telefone excluído com sucesso!");
                request.setAttribute("modelTelefones", modelTelefones);
                request.setAttribute("modelLogin", modelLogin);
                request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

                return;
            }

            String iduser = request.getParameter("iduser");

            if (iduser != null && !iduser.isEmpty()) {
                ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(iduser));
                List<ModelTelefone> modelTelefones = daoTelefoneRepository.listaFone(modelLogin.getId());

                request.setAttribute("modelTelefones", modelTelefones);
                request.setAttribute("modelLogin", modelLogin);
                request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

            } else {
                List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
                request.setAttribute("modelLogins", modelLogins);
                request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String usuario_pai_id = request.getParameter("id");
            String numero = request.getParameter("numero");

            if (!daoTelefoneRepository.existeFone(numero, Long.valueOf(usuario_pai_id))) {
                ModelTelefone modelTelefone = new ModelTelefone();
                modelTelefone.setNumero(numero);
                modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioID(Long.parseLong(usuario_pai_id)));
                modelTelefone.setUsuario_cad_id(super.getUserLogadoObj(request));
                daoTelefoneRepository.gravaTelefone(modelTelefone);
                request.setAttribute("msg", "Salvo com sucesso!");
            } else {
                request.setAttribute("msg", "Telefone já existe!");
            }

            List<ModelTelefone> modelTelefones = daoTelefoneRepository.listaFone(Long.parseLong(usuario_pai_id));
            ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(usuario_pai_id));
            request.setAttribute("modelLogin", modelLogin);
            request.setAttribute("modelTelefones", modelTelefones);
            request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
