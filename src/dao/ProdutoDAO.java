package dao;

import connection.ConnectionSingle;
import domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO{
    @Override
    public Integer cadastrar(Produto produto) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionSingle.getConnection();
            String sql = getSqlInsert();
            stm = connection.prepareStatement(sql);
            adicionarParametrosInsert(stm , produto);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }
    private String getSqlInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_produto (ID, CODIGO, NOME) ");
        sb.append("VALUES (nextval('sq_produto'), ?, ?)");
        return sb.toString();
    }
    private void adicionarParametrosInsert(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getCodigo());
        stm.setString(2, produto.getNome());
    }

    @Override
    public Integer atualizar(Produto produto) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionSingle.getConnection();
            String sql = getSqlUpdate();
            stm = connection.prepareStatement(sql);
            adicionarParametrosUpdate(stm , produto);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }
    private String getSqlUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tb_produto ");
        sb.append("SET NOME = ?, CODIGO = ? ");
        sb.append("WHERE ID = ?");
        return sb.toString();
    }
    private void adicionarParametrosUpdate(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getNome());
        stm.setString(2, produto.getCodigo());
        stm.setLong(3, produto.getId());
    }
    @Override
    public Integer exlcuir(Produto produto) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionSingle.getConnection();
            String sql = getSqlDelete();
            stm = connection.prepareStatement(sql);
            adicionarParametrosDelete(stm , produto);
            return stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }
    private String getSqlDelete() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM tb_produto ");
        sb.append("WHERE CODIGO = ?");
        return sb.toString();
    }
    private void adicionarParametrosDelete(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getCodigo());
    }

    @Override
    public Produto buscar(String codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;
        try {
            connection = ConnectionSingle.getConnection();
            String sql = getSqlSelect();
            stm = connection.prepareStatement(sql);
            adicionarParametrosSelect(stm , codigo);
            rs = stm.executeQuery();

            if(rs.next()){
                produto = new Produto();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cd = rs.getString("CODIGO");
                produto.setId(id);
                produto.setNome(nome);
                produto.setCodigo(cd);
            }
            return produto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, rs);
        }
    }
    private String getSqlSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_produto ");
        sb.append("WHERE CODIGO = ?");
        return sb.toString();
    }
    private void adicionarParametrosSelect(PreparedStatement stm, String codigo) throws SQLException {
        stm.setString(1, codigo);
    }

    @Override
    public List<Produto> buscarTodos() throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Produto> list = new ArrayList<>();
        try {
            connection = ConnectionSingle.getConnection();
            String sql = getSqlSelectAll();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while(rs.next()){
                Produto produto = new Produto();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cd = rs.getString("CODIGO");
                produto.setId(id);
                produto.setNome(nome);
                produto.setCodigo(cd);
                list.add(produto);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection, stm, rs);
        }
    }
    private String getSqlSelectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_produto");
        return sb.toString();
    }

    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) throws SQLException {
        if(rs != null && !rs.isClosed()){
            rs.close();
        }
        if(stm != null && !stm.isClosed()){
            stm.close();
        }
        if(connection != null && !connection.isClosed()){
            connection.close();
        }
    }
}
