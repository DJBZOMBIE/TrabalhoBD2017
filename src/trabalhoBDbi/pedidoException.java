package trabalhoBDbi;

import trabalhoBD.model.Produto;

public class pedidoException extends Exception {
	//variaveiss
	private static final long serialVersionUID = 1L;
	private Produto product;
	
	private int quantity;
	
	public pedidoException(Produto product, int quantity) { //construtor
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public pedidoException() {
		// TODO Auto-generated constructor stub
	}

	public Produto getProduct() {
		return product;
	}

	public void setProduct(Produto product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
