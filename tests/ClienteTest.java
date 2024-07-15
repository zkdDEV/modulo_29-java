import dao.ClienteDAO;
import dao.IClienteDAO;
import domain.Cliente;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ClienteTest {

    private IClienteDAO clienteDAO;

    @Test
    public void cadastrarTest(){
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Matheus Reichemback Stang");
        Integer countCad = clienteDAO.cadastrar(cliente);
        Assert.assertTrue(countCad == 1);

        Cliente clienteBD = clienteDAO.buscar("10");
        Assert.assertNotNull(clienteBD);
        Assert.assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        Assert.assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer countDel = clienteDAO.excluir(clienteBD);
        Assert.assertTrue(countDel == 1);
    }

    @Test
    public void buscarTest(){
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Matheus Reichemback Stang");
        Integer countCad = clienteDAO.cadastrar(cliente);
        Assert.assertTrue(countCad == 1);

        Cliente clienteBD = clienteDAO.buscar("10");
        Assert.assertNotNull(clienteBD);
        Assert.assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        Assert.assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer countDel = clienteDAO.excluir(clienteBD);
        Assert.assertTrue(countDel == 1);
    }

    @Test
    public void excluirTest(){
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Matheus Reichemback Stang");
        Integer countCad = clienteDAO.cadastrar(cliente);
        Assert.assertTrue(countCad == 1);

        Cliente clienteBD = clienteDAO.buscar("10");
        Assert.assertNotNull(clienteBD);
        Assert.assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        Assert.assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer countDel = clienteDAO.excluir(clienteBD);
        Assert.assertTrue(countDel == 1);
    }

    @Test
    public void buscarTodosTest(){
        clienteDAO = new ClienteDAO();

        Cliente primeiroCliente = new Cliente();
        primeiroCliente.setCodigo("10");
        primeiroCliente.setNome("Matheus Reichemback Stang");
        Integer countCad1 = clienteDAO.cadastrar(primeiroCliente);
        Assert.assertTrue(countCad1 == 1);

        Cliente segundoCliente = new Cliente();
        segundoCliente.setCodigo("300");
        segundoCliente.setNome("Regiane Aparecida Reichemback Stang");
        Integer countCad2 = clienteDAO.cadastrar(segundoCliente);
        Assert.assertTrue(countCad2 == 1);

        List<Cliente> list = clienteDAO.buscarTodos();
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());

        int countDel = 0;
        for(Cliente cliente : list){
            clienteDAO.excluir(cliente);
            countDel++;
        }
        Assert.assertEquals(list.size(), countDel);

        list = clienteDAO.buscarTodos();
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    public void atualizarTest(){
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Matheus Reichemback Stang");
        Integer countCad = clienteDAO.cadastrar(cliente);
        Assert.assertTrue(countCad == 1);

        Cliente clienteBD = clienteDAO.buscar("10");
        Assert.assertNotNull(clienteBD);
        Assert.assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        Assert.assertEquals(cliente.getNome(), clienteBD.getNome());

        clienteBD.setCodigo("20");
        clienteBD.setNome("Outro nome");
        Integer countUpdate = clienteDAO.atualizar(clienteBD);
        Assert.assertTrue(countUpdate == 1);

        Cliente clienteBDQueVaiDarErrado = clienteDAO.buscar("10");
        Assert.assertNull(clienteBDQueVaiDarErrado);

        Cliente clienteBDQueVaiDarCerto = clienteDAO.buscar("20");
        Assert.assertNotNull(clienteBDQueVaiDarCerto);
        Assert.assertEquals(clienteBD.getCodigo(), clienteBDQueVaiDarCerto.getCodigo());
        Assert.assertEquals(clienteBD.getNome(), clienteBDQueVaiDarCerto.getNome());
        Assert.assertEquals(clienteBD.getId(), clienteBDQueVaiDarCerto.getId());

        List<Cliente> list = clienteDAO.buscarTodos();
        for(Cliente cli : list){
            clienteDAO.excluir(cli);
        }
    }

}
