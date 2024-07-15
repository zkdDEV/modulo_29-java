package dao;

import domain.Cliente;

import java.util.List;

public interface IClienteDAO {

    public Integer cadastrar(Cliente cliente);
    public Integer atualizar(Cliente cliente);
    public Cliente buscar(String codigo);
    public List<Cliente> buscarTodos();
    public Integer excluir(Cliente cliente);
}
