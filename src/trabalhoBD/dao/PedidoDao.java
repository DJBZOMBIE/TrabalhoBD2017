package trabalhoBD.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import trabalhoBD.model.Pedido;



public class PedidoDao {
	private Connection connection;
	
	public PedidoDao() throws DAOException{
		connection = ConexaoBD.createConnection();
	}
	
	public PedidoDao(Connection connection){
		this.connection = connection;
	}
	
	private int nextCode() throws DAOException {
		try {
			String sql = "SELECT nextval('seq_pedido')";
			ResultSet result = connection.createStatement().executeQuery(sql);

			if(result.next()) {
				return result.getInt(1);
			}
			
			throw new DAOException("N�o foi poss�vel pegar o valor da sequ�ncia");
		} catch (SQLException exception) {
			throw new DAOException(exception);
		}
	}
	
	public void insert(Pedido pedido) throws DAOException{
		try{
			// Buscando o pr�ximo valor da sequ�ncia e atribuindo ao objeto
			pedido.setCod(nextCode());
			
			String sql ="INSERT INTO pedido (cod, data, cod_cliente) VALUES (?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, pedido.getCod());
			statement.setTimestamp(2, new java.sql.Timestamp(pedido.getData().getTime()));
			statement.setInt(3, pedido.getCod_cliente());
			
			statement.executeUpdate();
		} catch(SQLException exception){
			throw new DAOException(exception);
		}
	}
	
}
