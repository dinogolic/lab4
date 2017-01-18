package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class RegistracijaHandler {
	public static boolean registrujSe(RegistracijaBean reg) {
		try	{
			Connection conn = DBConnection.getConnection();
		    PreparedStatement stmt = conn.prepareStatement("insert into smartparking.korisnik(korisnickoime, sifra, ime, prezime) values(?,?,?,?)");
		    stmt.setString(1, reg.getName());
		    stmt.setString(2, reg.getPwd());
		    stmt.setString(3, reg.getIme());
		    stmt.setString(4, reg.getPrezime());
		    
		    stmt.executeUpdate();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}

}
