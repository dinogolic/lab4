package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class LoginHandler {
	
	public boolean checkLogin (String username, String pass)  {
		boolean l = false;
		try {
		LoginBean lB = new LoginBean();
		lB = getLogin(username);
		if (lB != null){

			if (lB.getPwd().equals(pass)) l = true;
		}
			
	
		}
		catch (Exception e) {
			l = false;
			System.out.println("Check login: " + e.toString());
		}
		
		
		
		return l;
		
	}
	public LoginBean getLogin(String username) {
		LoginBean loginBean = new LoginBean();	
		try {
				
			    Connection conn = DBConnection.getConnection();
			    System.out.println(username);
			    System.out.println("pretrazivanje baze");
			    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM smartparking.korisnik  WHERE korisnickoime = ?");
			    System.out.println(username);
			    stmt.setString(1, username);
			    
			    ResultSet rs = stmt.executeQuery();
			    
			    if (rs.next()) {	
			   
			    loginBean.setName(rs.getString("ime") + " " + rs.getString("prezime"));
			    loginBean.setUserid(rs.getString("id"));
			    loginBean.setPwd(rs.getString("sifra"));
				
			    return loginBean;
			    }
			  
				
			} catch (Exception e) {   
				System.out.println("Nije uspjela konekcija prema bazi podataka: " + e.toString());
			}
			
			return null;
		    }
	
	public ArrayList<LoginBean> getAllUsers() {
		ArrayList<LoginBean> result = new ArrayList<LoginBean>();
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM smartparking.korisnik");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				LoginBean d = new LoginBean();
				d.setName(rs.getString("ime"));
				d.setUserid(rs.getString("id"));
				d.setPwd(rs.getString("sifra"));
			
				result.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	 
}
