package scuola;

public class Segreteria {

	private String username;
	private int password;
	
	public Segreteria(String username) {
		this.username=username;
	}


	
	public void cambiaPasswordProf(Professore p, String pw) {
		p.setPassword(pw);
	}
	
	public void cambiaPasswordStudente(Studente s,String pw) {
		s.setPassword(pw);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
