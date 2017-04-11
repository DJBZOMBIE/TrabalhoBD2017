import java.util.ArrayList;

import trabalhoBD.model.Cliente;
import trabalhoBD.model.Item;
import trabalhoBDbi.pedidoBI;
import trabalhoBDbi.pedidoException;



public class testePedido {
	public static void main(String[] args) {
		
		try {
			Cliente client = new Cliente();
			client.setCod(1);

			ArrayList<Item> items = new ArrayList<Item>();
			
			//inserindo item 1 do pedido
			Item item1 = new Item();
			item1.setCod_produto(1);
			item1.setQuantidade(0);
			
			//insere na classe ItemBI(OrderBI)
			items.add(item1);
			
			// Inserindo o Item 2 do pedido
			Item item2 = new Item();
			item2.setCod_produto(3);
			item2.setQuantidade(0);
			System.out.println("blz2");
			items.add(item2);
			
			pedidoBI.createOrder(client, items);
			
			
		} catch (pedidoException exception) {
			System.out.println("Produto " + exception.getProduct().getNome() + " não tem saldo suficiente para a venda de " + exception.getQuantity() + " itens");
		}
		
	}
}
