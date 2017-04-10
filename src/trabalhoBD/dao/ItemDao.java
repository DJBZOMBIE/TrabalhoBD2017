package trabalhoBD.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import trabalhoBD.model.Item;

public class ItemDao {
	private Connection connection;
	
	public ItemDao() throws DAOException{
		connection = ConexaoBD.createConnection();
	}
	
	public ItemDao(Connection connection){ //construtor
		this.connection = connection;
	}
	
	//metodo de inserir
	public void insert(Item item) throws DAOException{
		try{
			String sql = "INSERT INTO item (cod_pedido, cod_produto, quantidade) VALUES (?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, item.getCod_pedido());
			statement.setInt(2, item.getCod_produto());
			statement.setInt(3, item.getQuantidade());
			
			statement.executeUpdate();
			
		}catch (SQLException exception){
			throw new DAOException(exception);
		}
	}
}
