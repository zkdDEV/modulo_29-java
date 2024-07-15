import dao.IProdutoDAO;
import dao.ProdutoDAO;
import domain.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class ProdutoTests {

    private IProdutoDAO produtoDAO;

    @Test
    public void cadastrarTest() throws SQLException {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("123");
        produto.setNome("Celular");

        Integer countCad = produtoDAO.cadastrar(produto);
        Assert.assertTrue(countCad == 1);

        Produto produtoBD = produtoDAO.buscar(produto.getCodigo());
        Assert.assertNotNull(produtoBD);
        Assert.assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        Assert.assertEquals(produto.getNome(), produtoBD.getNome());

        Integer countDel = produtoDAO.exlcuir(produtoBD);
        Assert.assertTrue(countDel == 1);
    }

    @Test
    public void buscarTest() throws SQLException {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("123");
        produto.setNome("Celular");

        Integer countCad = produtoDAO.cadastrar(produto);
        Assert.assertTrue(countCad == 1);

        Produto produtoBD = produtoDAO.buscar(produto.getCodigo());
        Assert.assertNotNull(produtoBD);
        Assert.assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        Assert.assertEquals(produto.getNome(), produtoBD.getNome());

        Integer countDel = produtoDAO.exlcuir(produtoBD);
        Assert.assertTrue(countDel == 1);
    }

    @Test
    public void excluirTest() throws SQLException {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("123");
        produto.setNome("Celular");

        Integer countCad = produtoDAO.cadastrar(produto);
        Assert.assertTrue(countCad == 1);

        Produto produtoBD = produtoDAO.buscar(produto.getCodigo());
        Assert.assertNotNull(produtoBD);
        Assert.assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        Assert.assertEquals(produto.getNome(), produtoBD.getNome());

        Integer countDel = produtoDAO.exlcuir(produtoBD);
        Assert.assertTrue(countDel == 1);
    }

    @Test
    public void atualizarTest() throws SQLException {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("123");
        produto.setNome("Celular");

        Integer countCad = produtoDAO.cadastrar(produto);
        Assert.assertTrue(countCad == 1);

        Produto produtoBD = produtoDAO.buscar(produto.getCodigo());
        Assert.assertNotNull(produtoBD);
        Assert.assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        Assert.assertEquals(produto.getNome(), produtoBD.getNome());

        produtoBD.setCodigo("321");
        produtoBD.setNome("Computador");
        Integer countAtu = produtoDAO.atualizar(produtoBD);
        Assert.assertTrue(countAtu == 1);
        Assert.assertNotEquals(produto.getNome(), produtoBD.getNome());
        Assert.assertNotEquals(produto.getCodigo(), produtoBD.getCodigo());

        Integer countDel = produtoDAO.exlcuir(produtoBD);
        Assert.assertTrue(countDel == 1);
    }

    @Test
    public void buscarTodosTest() throws SQLException {
        produtoDAO = new ProdutoDAO();

        Produto produto1 = new Produto();
        produto1.setCodigo("123");
        produto1.setNome("Celular");

        Produto produto2 = new Produto();
        produto2.setCodigo("321");
        produto2.setNome("Computador");

        Integer countCad1 = produtoDAO.cadastrar(produto1);
        Assert.assertTrue(countCad1 == 1);
        Integer countCad2 = produtoDAO.cadastrar(produto2);
        Assert.assertTrue(countCad2 == 1);

        List<Produto> list = produtoDAO.buscarTodos();
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());

        int countDel = 0;
        for(Produto produto : list){
            produtoDAO.exlcuir(produto);
            countDel++;
        }
        Assert.assertEquals(list.size(), countDel);

        list = produtoDAO.buscarTodos();
        Assert.assertEquals(0, list.size());
    }
}
