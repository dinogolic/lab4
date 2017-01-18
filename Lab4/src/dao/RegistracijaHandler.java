package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

	public Map<String, String> dajParkinge(String naziv) {
		try	{
			Connection conn = DBConnection.getConnection();
		    PreparedStatement stmt = conn.prepareStatement("select * from smartparking.parking pa where grad_id = ? and kapacitet > (select count(*) from smartparking.rezervacija where parking_id = pa.id and vrijeme_isticanja > now());");
		    stmt.setString(1, naziv);
		    ResultSet rs = stmt.executeQuery();
		    Map<String, String> gradovi = new HashMap();
		    while(rs.next()) {
		    	gradovi.put(String.valueOf(rs.getInt("id")), rs.getString("adresa") + " - " + rs.getString("cijena") + "KM/h");
		    }
			return gradovi;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static int dajIdKorisnika(String naziv) {
		try	{
			Connection conn = DBConnection.getConnection();
		    PreparedStatement stmt = conn.prepareStatement("select id from smartparking.korisnik where korisnickoime = ?");
		    stmt.setString(1, naziv);
		    ResultSet rs = stmt.executeQuery();
		    while(rs.next()) {
		    	return rs.getInt("id");
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 1;
	}

	public boolean rezervisi(RezervacijaBean rezervacijaBean) {
		try	{
			Connection conn = DBConnection.getConnection();
		    PreparedStatement stmt = conn.prepareStatement("insert into smartparking.rezervacija(korisnik_id, parking_id, vrijeme_isticanja, vrijeme_rezervacije) values(?,?,?,?)");
		    stmt.setInt(1, dajIdKorisnika(LoginBean.userid));
		    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    Date date = new Date();
		    Calendar cl = Calendar.getInstance();
		      cl.setTime(date);
		      cl.add(Calendar.HOUR, Integer.valueOf(rezervacijaBean.getSati()));
		    stmt.setInt(2, rezervacijaBean.getNaziv());
		    stmt.setString(3, dateFormat.format(cl.getTime()));
		    stmt.setString(4, dateFormat.format(date));
		    stmt.executeUpdate();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}

}
