package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
	
	public static Map<String, String> dajGradove() {
		try	{
			Connection conn = DBConnection.getConnection();
		    PreparedStatement stmt = conn.prepareStatement("select * from smartparking.grad;");
		    
		    ResultSet rs = stmt.executeQuery();
		    Map<String, String> gradovi = new HashMap();
		    while(rs.next()) {
		    	gradovi.put(String.valueOf(rs.getInt("id")), rs.getString("naziv"));
		    }
			return gradovi;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

}
