package trabalhoBD.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
	
	public static Connection createConnection() throws DAOException{
		try{
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/loja", "postgres", "1234");
		}catch (SQLException exception){
			throw new DAOException(exception);
		}
	}
}
