package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

    private final Connection connection;

    public DAOUsuarioRepository() {
        connection = SingleConnectionBanco.getConnection();
    }

    public ModelLogin gravarUsuario(ModelLogin objeto, Long userLogado) throws Exception {
        if (objeto.isNovo()) {
            String sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil, sexo, cep, logradouro, bairro, localidade, uf, numero, datanascimento)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedSql = connection.prepareStatement(sql);
            preparedSql.setString(1, objeto.getLogin());
            preparedSql.setString(2, objeto.getSenha());
            preparedSql.setString(3, objeto.getNome());
            preparedSql.setString(4, objeto.getEmail());
            preparedSql.setLong(5, userLogado);
            preparedSql.setString(6, objeto.getPerfil());
            preparedSql.setString(7, objeto.getSexo());
            preparedSql.setString(8, objeto.getCep());
            preparedSql.setString(9, objeto.getLogradouro());
            preparedSql.setString(10, objeto.getBairro());
            preparedSql.setString(11, objeto.getLocalidade());
            preparedSql.setString(12, objeto.getUf());
            preparedSql.setString(13, objeto.getNumero());
            preparedSql.setDate(14, objeto.getDataNascimento());
            preparedSql.execute();
            connection.commit();
        } else {
            String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?, perfil=?, sexo=?, cep=?, logradouro=?, bairro =?, localidade=?, uf=?, numero=?, datanascimento=? WHERE id=  "+objeto.getId()+";";

            PreparedStatement prepareSql = connection.prepareStatement(sql);
            prepareSql.setString(1, objeto.getLogin());
            prepareSql.setString(2, objeto.getSenha());
            prepareSql.setString(3, objeto.getNome());
            prepareSql.setString(4, objeto.getEmail());
            prepareSql.setString(5, objeto.getPerfil());
            prepareSql.setString(6, objeto.getSexo());
            prepareSql.setString(7, objeto.getCep());
            prepareSql.setString(8, objeto.getLogradouro());
            prepareSql.setString(9, objeto.getBairro());
            prepareSql.setString(10, objeto.getLocalidade());
            prepareSql.setString(11, objeto.getUf());
            prepareSql.setString(12, objeto.getNumero());
            prepareSql.setDate(13, objeto.getDataNascimento());
            prepareSql.executeUpdate();
            connection.commit();
        }
        return this.consultaUsuario(objeto.getLogin(), userLogado);
    }

    public List<ModelLogin> consultaUsuarioListPaginada(Long userLogado, Integer offset) throws Exception {
        List<ModelLogin> retorno = new ArrayList<ModelLogin>();
        String sql = "SELECT * FROM model_login WHERE useradmin IS FALSE AND usuario_id = " + userLogado + " ORDER BY nome OFFSET "+offset+" LIMIT 5";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {
            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setEmail(resultado.getString("email"));
            modelLogin.setId(resultado.getLong("id"));
            modelLogin.setLogin(resultado.getString("login"));
            modelLogin.setNome(resultado.getString("nome"));
            modelLogin.setPerfil(resultado.getString("perfil"));
            modelLogin.setSexo(resultado.getString("sexo"));
            retorno.add(modelLogin);
        }
        return retorno;
    }

    public int totalPagina(Long userLogado) throws Exception {
        String sql = "SELECT COUNT(1) AS total FROM model_login  WHERE usuario_id = " + userLogado;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();
        resultado.next();

        Double cadastros = resultado.getDouble("total");
        Double porpagina = 5.0;
        Double pagina = cadastros / porpagina;
        Double resto = pagina % 2;

        if (resto > 0) {
            pagina ++;
        }
        return pagina.intValue();
    }

    public List<ModelLogin> consultaUsuarioList(Long userLogado) throws Exception {
        List<ModelLogin> retorno = new ArrayList<ModelLogin>();
        String sql = "SELECT * FROM model_login WHERE useradmin IS FALSE AND usuario_id = " + userLogado + " LIMIT 5";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {
            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setEmail(resultado.getString("email"));
            modelLogin.setId(resultado.getLong("id"));
            modelLogin.setLogin(resultado.getString("login"));
            modelLogin.setNome(resultado.getString("nome"));
            modelLogin.setPerfil(resultado.getString("perfil"));
            modelLogin.setSexo(resultado.getString("sexo"));
            retorno.add(modelLogin);
        }
        return retorno;
    }

    public int consultaUsuarioListTotalPaginaPaginacao(String nome, Long userLogado) throws Exception {
        String sql = "SELECT COUNT(1) AS total FROM model_login  WHERE UPPER(nome) LIKE UPPER(?) AND useradmin IS FALSE AND usuario_id=? ";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + nome + "%");
        statement.setLong(2, userLogado);
        ResultSet resultado = statement.executeQuery();
        resultado.next();

        Double cadastros = resultado.getDouble("total");
        Double porpagina = 5.0;
        Double pagina = cadastros / porpagina;
        Double resto = pagina % 2;

        if (resto > 0) {
            pagina ++;
        }
        return pagina.intValue();
    }

    public List<ModelLogin> consultaUsuarioListOffSet(String nome, Long userLogado, int offset) throws Exception {
        List<ModelLogin> retorno = new ArrayList<ModelLogin>();
        String sql = "SELECT * FROM model_login  WHERE UPPER(nome) LIKE UPPER(?) AND useradmin IS FALSE AND usuario_id = ? OFFSET "+offset+" LIMIT 5";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + nome + "%");
        statement.setLong(2, userLogado);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {
            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setEmail(resultado.getString("email"));
            modelLogin.setId(resultado.getLong("id"));
            modelLogin.setLogin(resultado.getString("login"));
            modelLogin.setNome(resultado.getString("nome"));
            modelLogin.setPerfil(resultado.getString("perfil"));
            modelLogin.setSexo(resultado.getString("sexo"));
            retorno.add(modelLogin);
        }
        return retorno;
    }

    public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws Exception {
        List<ModelLogin> retorno = new ArrayList<ModelLogin>();
        String sql = "SELECT * FROM model_login  WHERE UPPER(nome) LIKE UPPER(?) AND useradmin IS FALSE AND usuario_id = ? LIMIT 5";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + nome + "%");
        statement.setLong(2, userLogado);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {
            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setEmail(resultado.getString("email"));
            modelLogin.setId(resultado.getLong("id"));
            modelLogin.setLogin(resultado.getString("login"));
            modelLogin.setNome(resultado.getString("nome"));
            modelLogin.setPerfil(resultado.getString("perfil"));
            modelLogin.setSexo(resultado.getString("sexo"));
            retorno.add(modelLogin);
        }
        return retorno;
    }

    public ModelLogin consultaUsuarioLogado(String login) throws Exception  {
        ModelLogin modelLogin = new ModelLogin();
        String sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER('"+login+"')";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado =  statement.executeQuery();

        while (resultado.next()) {
            modelLogin.setId(resultado.getLong("id"));
            modelLogin.setEmail(resultado.getString("email"));
            modelLogin.setLogin(resultado.getString("login"));
            modelLogin.setSenha(resultado.getString("senha"));
            modelLogin.setNome(resultado.getString("nome"));
            modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
            modelLogin.setPerfil(resultado.getString("perfil"));
            modelLogin.setSexo(resultado.getString("sexo"));
            modelLogin.setCep(resultado.getString("cep"));
            modelLogin.setLogradouro(resultado.getString("logradouro"));
            modelLogin.setBairro(resultado.getString("bairro"));
            modelLogin.setLocalidade(resultado.getString("localidade"));
            modelLogin.setUf(resultado.getString("uf"));
            modelLogin.setNumero(resultado.getString("numero"));
        }
        return modelLogin;
    }

    public ModelLogin consultaUsuario(String login) throws Exception  {
        ModelLogin modelLogin = new ModelLogin();
        String sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER('"+login+"') AND useradmin IS FALSE ";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado =  statement.executeQuery();

        while (resultado.next()) {
            modelLogin.setId(resultado.getLong("id"));
            modelLogin.setEmail(resultado.getString("email"));
            modelLogin.setLogin(resultado.getString("login"));
            modelLogin.setSenha(resultado.getString("senha"));
            modelLogin.setNome(resultado.getString("nome"));
            modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
            modelLogin.setPerfil(resultado.getString("perfil"));
            modelLogin.setSexo(resultado.getString("sexo"));
            modelLogin.setCep(resultado.getString("cep"));
            modelLogin.setLogradouro(resultado.getString("logradouro"));
            modelLogin.setBairro(resultado.getString("bairro"));
            modelLogin.setLocalidade(resultado.getString("localidade"));
            modelLogin.setUf(resultado.getString("uf"));
            modelLogin.setNumero(resultado.getString("numero"));
        }
        return modelLogin;
    }

    public ModelLogin consultaUsuario(String login, Long userLogado) throws Exception  {
        ModelLogin modelLogin = new ModelLogin();
        String sql = "SELECT * FROM model_login WHERE UPPER(login) = UPPER('"+login+"') AND useradmin IS FALSE AND usuario_id = " + userLogado;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado =  statement.executeQuery();

        while (resultado.next()) {
            modelLogin.setId(resultado.getLong("id"));
            modelLogin.setEmail(resultado.getString("email"));
            modelLogin.setLogin(resultado.getString("login"));
            modelLogin.setSenha(resultado.getString("senha"));
            modelLogin.setNome(resultado.getString("nome"));
            modelLogin.setPerfil(resultado.getString("perfil"));
            modelLogin.setSexo(resultado.getString("sexo"));
            modelLogin.setCep(resultado.getString("cep"));
            modelLogin.setLogradouro(resultado.getString("logradouro"));
            modelLogin.setBairro(resultado.getString("bairro"));
            modelLogin.setLocalidade(resultado.getString("localidade"));
            modelLogin.setUf(resultado.getString("uf"));
            modelLogin.setNumero(resultado.getString("numero"));
        }
        return modelLogin;
    }

    public ModelLogin consultaUsuarioID(Long id) throws Exception  {
        ModelLogin modelLogin = new ModelLogin();
        String sql = "SELECT * FROM model_login WHERE id = ? AND useradmin IS FALSE";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultado =  statement.executeQuery();

        while (resultado.next()) {
            modelLogin.setId(resultado.getLong("id"));
            modelLogin.setEmail(resultado.getString("email"));
            modelLogin.setLogin(resultado.getString("login"));
            modelLogin.setSenha(resultado.getString("senha"));
            modelLogin.setNome(resultado.getString("nome"));
            modelLogin.setPerfil(resultado.getString("perfil"));
            modelLogin.setSexo(resultado.getString("sexo"));
            modelLogin.setCep(resultado.getString("cep"));
            modelLogin.setLogradouro(resultado.getString("logradouro"));
            modelLogin.setBairro(resultado.getString("bairro"));
            modelLogin.setLocalidade(resultado.getString("localidade"));
            modelLogin.setUf(resultado.getString("uf"));
            modelLogin.setNumero(resultado.getString("numero"));
        }
        return modelLogin;
    }

    public ModelLogin consultaUsuarioID(String id, Long userLogado) throws Exception  {
        ModelLogin modelLogin = new ModelLogin();
        String sql = "SELECT * FROM model_login WHERE id = ? AND useradmin IS FALSE AND usuario_id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, Long.parseLong(id));
        statement.setLong(2, userLogado);
        ResultSet resultado =  statement.executeQuery();

        while (resultado.next()) {
            modelLogin.setId(resultado.getLong("id"));
            modelLogin.setEmail(resultado.getString("email"));
            modelLogin.setLogin(resultado.getString("login"));
            modelLogin.setSenha(resultado.getString("senha"));
            modelLogin.setNome(resultado.getString("nome"));
            modelLogin.setPerfil(resultado.getString("perfil"));
            modelLogin.setSexo(resultado.getString("sexo"));
            modelLogin.setCep(resultado.getString("cep"));
            modelLogin.setLogradouro(resultado.getString("logradouro"));
            modelLogin.setBairro(resultado.getString("bairro"));
            modelLogin.setLocalidade(resultado.getString("localidade"));
            modelLogin.setUf(resultado.getString("uf"));
            modelLogin.setNumero(resultado.getString("numero"));
        }
        return modelLogin;
    }

    public boolean validarLogin(String login) throws Exception {
        String sql = "SELECT COUNT(1) > 0 AS existe FROM model_login WHERE UPPER(login) = UPPER('"+login+"');";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resutlado =  statement.executeQuery();
        resutlado.next();
        return resutlado.getBoolean("existe");
    }

    public void deletarUser(String idUser) throws Exception {
        String sql = "DELETE FROM model_login WHERE id = ? and useradmin is false;";

        PreparedStatement prepareSql = connection.prepareStatement(sql);
        prepareSql.setLong(1, Long.parseLong(idUser));
        prepareSql.executeUpdate();
        connection.commit();
    }
}