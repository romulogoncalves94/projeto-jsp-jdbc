package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@MultipartConfig
@WebServlet( urlPatterns =  {"/ServletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {

    private static final long serialVersionUID = 1L;

    private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

    public ServletUsuarioController() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String acao  = request.getParameter("acao");

            if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
                String idUser = request.getParameter("id");
                daoUsuarioRepository.deletarUser(idUser);
                List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));

                request.setAttribute("modelLogins", modelLogins);
                request.setAttribute("msg", "Excluido com sucesso!");
                request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

            } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {

                String idUser = request.getParameter("id");
                daoUsuarioRepository.deletarUser(idUser);
                response.getWriter().write("Excluido com sucesso!");

            } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

                String nomeBusca = request.getParameter("nomeBusca");
                List<ModelLogin> dadosJsonUser =  daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getUserLogado(request));
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(dadosJsonUser);
                response.addHeader("totalPagina", ""+ daoUsuarioRepository.consultaUsuarioListTotalPaginaPaginacao(nomeBusca, super.getUserLogado(request)));
                response.getWriter().write(json);

            } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")) {

                String nomeBusca = request.getParameter("nomeBusca");
                String pagina = request.getParameter("pagina");
                List<ModelLogin> dadosJsonUser =  daoUsuarioRepository.consultaUsuarioListOffSet(nomeBusca, super.getUserLogado(request), Integer.parseInt(pagina));
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(dadosJsonUser);
                response.addHeader("totalPagina", ""+ daoUsuarioRepository.consultaUsuarioListTotalPaginaPaginacao(nomeBusca, super.getUserLogado(request)));
                response.getWriter().write(json);

            } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

                String id = request.getParameter("id");
                ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(id, super.getUserLogado(request));
                List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
                request.setAttribute("modelLogins", modelLogins);
                request.setAttribute("msg", "Usuário em edição");
                request.setAttribute("modolLogin", modelLogin);
                request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
            } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {

                List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
                request.setAttribute("msg", "Usuários carregados");
                request.setAttribute("modelLogins", modelLogins);
                request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

            } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {

                Integer offset = Integer.parseInt(request.getParameter("pagina"));
                List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioListPaginada(this.getUserLogado(request), offset);
                request.setAttribute("modelLogins", modelLogins);
                request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

            } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioUser")) {

                String dataInicial = request.getParameter("dataInicial");
                String dataFinal = request.getParameter("dataFinal");

                if (dataInicial == null && dataInicial.isEmpty() && dataFinal == null && dataFinal.isEmpty()) {
                    request.setAttribute("listaUser", daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request)));
                }

                request.setAttribute("dataInicial", dataInicial);
                request.setAttribute("dataFinal", dataFinal);
                request.getRequestDispatcher("principal/reluser.jsp").forward(request, response);

            } else {

                List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
                request.setAttribute("modelLogins", modelLogins);
                request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

            }

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
            request.setAttribute("msg", e.getMessage());
            redirecionar.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String msg = "Operação realizada com sucesso!";
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String perfil = request.getParameter("perfil");
            String sexo = request.getParameter("sexo");
            String cep = request.getParameter("cep");
            String logradouro = request.getParameter("logradouro");
            String bairro = request.getParameter("bairro");
            String localidade = request.getParameter("localidade");
            String uf = request.getParameter("uf");
            String numero = request.getParameter("numero");
            String dataNascimento = request.getParameter("dataNascimento");

            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
            modelLogin.setNome(nome);
            modelLogin.setEmail(email);
            modelLogin.setLogin(login);
            modelLogin.setSenha(senha);
            modelLogin.setPerfil(perfil);
            modelLogin.setSexo(sexo);
            modelLogin.setCep(cep);
            modelLogin.setLogradouro(logradouro);
            modelLogin.setBairro(bairro);
            modelLogin.setLocalidade(localidade);
            modelLogin.setUf(uf);
            modelLogin.setNumero(numero);
            modelLogin.setDataNascimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimento))));

            if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
                msg = "Já existe usuário com o mesmo login, informe outro login;";
            } else {
                if (modelLogin.isNovo()) {
                    msg = "Gravado com sucesso!";
                } else {
                    msg= "Atualizado com sucesso!";
                }
                modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));
            }

            List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
            request.setAttribute("modelLogins", modelLogins);
            request.setAttribute("msg", msg);
            request.setAttribute("modolLogin", modelLogin);
            request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
            request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
            request.setAttribute("msg", e.getMessage());
            redirecionar.forward(request, response);
        }
    }
}