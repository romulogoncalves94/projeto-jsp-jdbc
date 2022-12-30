package dao;

import connection.SingleConnectionBanco;
import model.ModelLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOLoginRepository {

    private Connection connection;

    public DAOLoginRepository() {
        connection = SingleConnectionBanco.getConnection();
    }

    public boolean validarAutenticacao(ModelLogin modelLogin) throws Exception{
        String sql = "SELECT * FROM model_login WHERE login = ? AND senha = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, modelLogin.getLogin());
        statement.setString(2, modelLogin.getSenha());
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            return true;
        }
        return false;
    }
}
