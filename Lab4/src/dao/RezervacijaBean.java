package dao;

public class RezervacijaBean {
	int naziv;
	public int getNaziv() {
		return naziv;
	}
	public void setNaziv(int naziv) {
		this.naziv = naziv;
	}
	public String getSati() {
		return sati;
	}
	public void setSati(String sati) {
		this.sati = sati;
	}
	String sati;
}
