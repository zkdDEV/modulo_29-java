package dao;

import connection.ConnectionSingle;
import domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{
    @Override
    public Integer cadastrar(Cliente cliente) {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionSingle.getConnection();
            String sql = getSqlInsert();
            stm = connection.prepareStatement(sql);
            adicionarParametrosInsert(stm, cliente);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//            Limpando os dados e fechadno a entrada para não dar erro - ISSO É ESSÊNCIAL
            closeConnection(connection, stm, null);
        }
    }
    private String getSqlInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_CLIENTE (ID, CODIGO, NOME) ");
        sb.append("VALUES (nextval('SQ_CLIENTE'), ?, ?)");
        return sb.toString();
    }
    private void adicionarParametrosInsert(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getCodigo());
        stm.setString(2, cliente.getNome());
    }

    @Override
    public Integer atualizar(Cliente cliente) {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionSingle.getConnection();
            String sql = getSqlUpdate();
            stm = connection.prepareStatement(sql);
            adicionarParametrosUpdate(stm, cliente);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }
    private String getSqlUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_CLIENTE ");
        sb.append("SET NOME = ?, CODIGO = ? ");
        sb.append("WHERE ID = ?");
        return sb.toString();
    }
    private void adicionarParametrosUpdate(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getNome());
        stm.setString(2, cliente.getCodigo());
        stm.setLong(3, cliente.getId());
    }

    @Override
    public Cliente buscar(String codigo) {
        Connection connection = null;
        PreparedStatement stm = null;
//        Armazena os dados quem são buscados a partir de um comando select
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            connection = ConnectionSingle.getConnection();
            String sql = getSqlSelect();
            stm = connection.prepareStatement(sql);
            adicionarParametrosSelect(stm, codigo);
            rs = stm.executeQuery();

            if(rs.next()){
                cliente = new Cliente();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cd = rs.getString("CODIGO");
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCodigo(cd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, rs);
        }
        return cliente;
    }
    private String getSqlSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM TB_CLIENTE ");
        sb.append("WHERE CODIGO = ?");
        return sb.toString();
    }
    private void adicionarParametrosSelect(PreparedStatement stm, String codigo) throws SQLException {
        stm.setString(1, codigo);
    }

    @Override
    public Integer excluir(Cliente cliente) {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionSingle.getConnection();
            String sql = getSqlDelete();
            stm = connection.prepareStatement(sql);
            adicionarParametrosDelete(stm, cliente);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }
    private String getSqlDelete() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM TB_CLIENTE ");
        sb.append("WHERE CODIGO = ?");
        return sb.toString();
    }
    private void adicionarParametrosDelete(PreparedStatement stm, Cliente cliente) throws SQLException {
        stm.setString(1, cliente.getCodigo());
    }

    @Override
    public List<Cliente> buscarTodos() {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Cliente> list = new ArrayList<>();
        Cliente cliente = null;
        try {
            connection = ConnectionSingle.getConnection();
            String sql = getSqlSelectAll();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while(rs.next()){
                cliente = new Cliente();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cd = rs.getString("CODIGO");
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCodigo(cd);
                list.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
        return list;
    }
    private String getSqlSelectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM TB_CLIENTE ");
        return sb.toString();
    }

    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
        try {
            if(rs != null && !rs.isClosed()){
                rs.close();
            }
            if(stm != null && !stm.isClosed()){
                stm.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
