import java.util.Date;

import trabalhoBD.dao.ConexaoBD;
import trabalhoBD.dao.DAOException;
import trabalhoBD.dao.ItemDao;
import trabalhoBD.dao.PedidoDao;
import trabalhoBD.model.Item;
import trabalhoBD.model.Pedido;



public class tryConnectionTest {
	
	public static void main(String[] args) {

		try {
			ConexaoBD.createConnection();
			System.out.println("Connection OK");
			
			Pedido order = new Pedido();
			order.setData(new Date());
			
			PedidoDao orderDAO = new PedidoDao(); //instancia conexao do pedido
			orderDAO.insert(order);
			
			System.out.println("Order inserted");
			
			
			Item item = new Item();
			item.setCod_pedido(101);
			item.setCod_produto(1);
			item.setQuantidade(10);
			
			ItemDao itemDAO = new ItemDao();
			itemDAO.insert(item);
			
			System.out.println("Item inserted");
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
}
