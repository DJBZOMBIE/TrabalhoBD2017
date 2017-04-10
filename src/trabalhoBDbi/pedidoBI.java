package trabalhoBDbi;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;


import trabalhoBD.dao.ConexaoBD;
import trabalhoBD.dao.ItemDao;
import trabalhoBD.dao.PedidoDao;
import trabalhoBD.model.Cliente;
import trabalhoBD.model.Item;
import trabalhoBD.model.Pedido;

public class pedidoBI {
	
	public static void createOrder(Cliente client, ArrayList<Item> items) throws pedidoException{
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
			for (Item item : items) {
				Produto product = productDAO.findForUpdate(item.getProductCode());//parei aqui===========
				
				if(product != null && product.getBalance() >= item.getQuantity()) {
					item.setOrderCode(order.getCode());
					itemDAO.insert(item);
					
					product.setBalance(product.getBalance() - item.getQuantity());
					productDAO.update(product);
				} else {
					throw new OrderException(product, item.getQuantity());
				}
			}
		}
	}
}
