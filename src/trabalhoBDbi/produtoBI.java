package trabalhoBDbi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import trabalhoBD.dao.ConexaoBD;
import trabalhoBD.dao.DAOException;
import trabalhoBD.dao.ItemDao;
import trabalhoBD.dao.PedidoDao;
import trabalhoBD.dao.ProdutoDao;
import trabalhoBD.model.Cliente;
import trabalhoBD.model.Item;
import trabalhoBD.model.Pedido;
import trabalhoBD.model.Produto;

public class produtoBI {
	public static void createOrder(ArrayList<Produto> prods) throws pedidoException{ //parei na criação do produto BI
		try{
			// Criar a conexão com o banco
			Connection connection = ConexaoBD.createConnection();
			
			// Iniciar a transação com setAutoCommit(false)
			connection.setAutoCommit(false);
						
			// Inserir o Pedido
			PedidoDao orderDAO = new PedidoDao(connection); //instancia dao do pedido
			
			Pedido order = new Pedido(); //instancia pedido
			order.setCod_cliente(client.getCod()); //pegando e setando os valores das variaveis
			order.setData(new Date());
			
			orderDAO.insert(order); // O código do pedido foi atribuído dentro do insert
			
			// Foreach(for) nos Itens, inserindo cada um dos itens
			ItemDao itemDao = new ItemDao(connection);
			ProdutoDao productDAO = new ProdutoDao(connection);
			
			for (Item item : items){
				Produto product = productDAO.findForUpdate(item.getCod_produto());//parei aqui===========
				
				if(product != null && product.getSaldo() >= item.getQuantidade()) {
					item.setCod_pedido(order.getCod());
					itemDao.insert(item);
					
					product.setSaldo(product.getSaldo() - item.getQuantidade());
					productDAO.update(product);
				} else {
					throw new pedidoException(product, item.getQuantidade());
				}
			}
			
			connection.commit();
		} catch (DAOException e){
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}
