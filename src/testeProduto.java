import java.util.ArrayList;

import trabalhoBD.model.Cliente;
import trabalhoBD.model.Item;
import trabalhoBD.model.Produto;
import trabalhoBDbi.pedidoBI;
import trabalhoBDbi.pedidoException;

public class testeProduto {
public static void main(String[] args) {
		
		try {
			Produto product = new Produto();
			product.setCod(1);

			ArrayList<Produto> products = new ArrayList<Produto>();
			
			//inserindo item 1 do pedido
			Produto prod1 = new Produto();
			prod1.setCod(1);
			prod1.setNome("PS4");
			prod1.setSaldo(5);
			//insere na classe ItemBI(OrderBI)
			products.add(prod1);
			
			pedidoBI.createOrder(client, items); //tentando inserir no produto
			
			
		} catch (pedidoException exception) {
			System.out.println("Produto " + exception.getProduct().getNome() + " não tem saldo suficiente para a venda de " + exception.getQuantity() + " itens");
		}
		
	}
}
