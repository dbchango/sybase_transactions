package sybase.dao;

import java.sql.SQLException;

public interface DeudorDAO {
	
	public int UpdateScore(String tramite) throws SQLException;
	
	public int InsertarDatosSabanaComercial(String tramite, String ente) throws SQLException;
	
	public int InsertarCapacidadDePagoComercial(String tramite, String ente) throws SQLException;
}
