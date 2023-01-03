package dao;

import connection.SingleConnectionBanco;
import model.ModelLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOUsuarioRepository {

   private Connection connection;

    public DAOUsuarioRepository() {
        connection = SingleConnectionBanco.getConnection();
    }

    public void gravarUsuario(ModelLogin modelLogin) throws SQLException {

        String sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedSql = connection.prepareStatement(sql);
        preparedSql.setString(1, modelLogin.getLogin());
        preparedSql.setString(2, modelLogin.getSenha());
        preparedSql.setString(3, modelLogin.getNome());
        preparedSql.setString(4, modelLogin.getEmail());

        preparedSql.execute();
        connection.commit();

    }
}
