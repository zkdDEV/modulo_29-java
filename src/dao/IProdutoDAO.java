package dao;

import domain.Produto;

import java.sql.SQLException;
import java.util.List;

public interface IProdutoDAO {

    public Integer cadastrar(Produto produto) throws SQLException;
    public Integer atualizar(Produto produto) throws SQLException;
    public Integer exlcuir(Produto produto) throws SQLException;
    public Produto buscar(String codigo) throws SQLException;
    public List<Produto> buscarTodos() throws SQLException;

}
