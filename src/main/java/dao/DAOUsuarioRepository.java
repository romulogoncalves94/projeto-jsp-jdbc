package dao;

import connection.SingleConnectionBanco;
import model.ModelLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUsuarioRepository {

   private Connection connection;

    public DAOUsuarioRepository() {
        connection = SingleConnectionBanco.getConnection();
    }

    public ModelLogin gravarUsuario(ModelLogin modelLogin) throws Exception {

        if(modelLogin.isNovo()) {
            String sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedSql = connection.prepareStatement(sql);
            preparedSql.setString(1, modelLogin.getLogin());
            preparedSql.setString(2, modelLogin.getSenha());
            preparedSql.setString(3, modelLogin.getNome());
            preparedSql.setString(4, modelLogin.getEmail());

            preparedSql.execute();
            connection.commit();
        } else {
            String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=? WHERE id="+modelLogin.getId()+"";
            PreparedStatement prepareSql = connection.prepareStatement(sql);
            prepareSql.setString(1, modelLogin.getLogin());
            prepareSql.setString(2, modelLogin.getSenha());
            prepareSql.setString(3, modelLogin.getNome());
            prepareSql.setString(4, modelLogin.getEmail());

            prepareSql.executeUpdate();
            connection.commit();
        }

        return this.consultaUsuario(modelLogin.getLogin());
    }

    public List<ModelLogin> consultarUsuarioList(String nome) throws Exception{
        List<ModelLogin> retorno = new ArrayList<ModelLogin>();

        String sql = "SELECT * FROM model_login WHERE UPPER(nome) LIKE UPPER (?) ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + nome + "%");
        ResultSet resultSet = statement.executeQuery();

        return getModelLogins(retorno, resultSet);
    }

    public List<ModelLogin> consultarUsuarioListAll() throws Exception{
        List<ModelLogin> retorno = new ArrayList<ModelLogin>();

        String sql = "SELECT * FROM model_login";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        return getModelLogins(retorno, resultSet);
    }

    private List<ModelLogin> getModelLogins(List<ModelLogin> retorno, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {

            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setEmail(resultSet.getString("email"));
            modelLogin.setId(resultSet.getLong("id"));
            modelLogin.setLogin(resultSet.getString("login"));
            modelLogin.setNome(resultSet.getString("nome"));

            retorno.add(modelLogin);

        }

        return retorno;
    }

    public ModelLogin consultaUsuario(String login) throws Exception{
        ModelLogin modelLogin = new ModelLogin();

        String sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER('"+login+"')";

        PreparedStatement statement = connection.prepareStatement(sql);
        return getModelLogin(modelLogin, statement);
    }

    public ModelLogin consultaUsuarioID(String id) throws Exception{
        ModelLogin modelLogin = new ModelLogin();

        String sql = "SELECT * FROM model_login WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, Long.parseLong(id));
        return getModelLogin(modelLogin, statement);
    }

    private ModelLogin getModelLogin(ModelLogin modelLogin, PreparedStatement statement) throws SQLException {
        ResultSet resultado = statement.executeQuery();

        while(resultado.next()) {
            modelLogin.setId(resultado.getLong("id"));
            modelLogin.setEmail(resultado.getString("email"));
            modelLogin.setLogin(resultado.getString("login"));
            modelLogin.setSenha(resultado.getString("senha"));
            modelLogin.setNome(resultado.getString("nome"));
        }
        return modelLogin;
    }

    public boolean validaLogin(String login) throws Exception{
        String sql = "SELECT COUNT(1) > 0 AS existe FROM model_login WHERE UPPER(login) = UPPER('"+login+"')";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        if(resultado.next()) {
            return resultado.getBoolean("existe");
        }

        return false;
    }

    public void deletarUser(String idUser) throws Exception{
        String sql = "DELETE FROM model_login WHERE id = ?";
        PreparedStatement prepareSql = connection.prepareStatement(sql);
        prepareSql.setLong(1, Long.parseLong(idUser));
        prepareSql.executeUpdate();
        connection.commit();
    }
}
