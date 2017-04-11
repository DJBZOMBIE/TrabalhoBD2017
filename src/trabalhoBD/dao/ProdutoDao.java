package trabalhoBD.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trabalhoBD.model.Produto;

public class ProdutoDao {
	
	private Connection connection;
	
	public ProdutoDao() throws DAOException{
		connection = ConexaoBD.createConnection();
	}
	
	public ProdutoDao(Connection connection){
		this.connection = connection;
	}
	
	public Produto findForUpdate(int code) throws DAOException{
		try{
			String sql = "SELECT * FROM produto WHERE cod = ? FOR UPDATE";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,  code);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()){
				Produto product = new Produto();
				
				product.setCod(code);
				product.setNome(result.getString("nome"));
				product.setSaldo(result.getInt("saldo"));
				
				return product;
			
			}else{
				return null;
			}
		}catch (SQLException exception) {
			throw new DAOException(exception);
		}
	}
	
	public void insert(Produto product) throws DAOException {
		try {
			String sql = "INSERT INTO produto (cod, nome, saldo) VALUES (?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, product.getCod());
			statement.setString(2, product.getNome());
			statement.setInt(3, product.getSaldo());
			
			statement.executeUpdate();
		} catch (SQLException exception) {
			throw new DAOException(exception);
		}
	}
	
	public void update(Produto product) throws DAOException{
		try{
			String sql = "UPDATE produto set nome = ?, saldo = ? WHERE cod = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, product.getNome());
			statement.setInt(2, product.getSaldo());
			statement.setInt(3, product.getCod());
			
			statement.executeQuery();
		}catch(SQLException exception){
			throw new DAOException(exception);
		}
	}
}
