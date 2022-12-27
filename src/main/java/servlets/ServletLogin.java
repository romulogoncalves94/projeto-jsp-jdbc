package servlets;

import jakarta.servlet.RequestDispatcher;
import model.ModelLogin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {

    public ServletLogin() {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String url = request.getParameter("url");

        if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()){
            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setLogin(login);
            modelLogin.setSenha(senha);

            if(modelLogin.getLogin().equalsIgnoreCase("admin") && modelLogin.getSenha().equalsIgnoreCase("admin")) {
                request.getSession().setAttribute("usuario", modelLogin);

                if(url == null || url.equals("null")) {
                    url = "principal/principal.jsp";
                }

                RequestDispatcher redirecionar = request.getRequestDispatcher(url);
                redirecionar.forward(request, response);
            } else {
                RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", "Informe o Login e Senha corretamente!");
                redirecionar.forward(request, response);
            }
        } else{
           RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
           request.setAttribute("msg", "Informe o Login e Senha corretamente!");
           redirecionar.forward(request, response);
        }
    }
}
