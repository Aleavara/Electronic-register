package scuola;

import java.time.LocalDate;

public class Voto {
private LocalDate dataInserimento;
private Double voto;
private Professore profInserente;
public Voto(LocalDate data,Double v,Professore prof) {
	this.setDataInserimento(data);
	this.setVoto(v);
	this.setProfInserente(prof);
}

public LocalDate getDataInserimento() {
	return dataInserimento;
}

public void setDataInserimento(LocalDate dataInserimento) {
	this.dataInserimento = dataInserimento;
}

public Double getVoto() {
	return voto;
}

public void setVoto(Double voto) {
	this.voto = voto;
}

public Professore getProfInserente() {
	return profInserente;
}

public void setProfInserente(Professore profInserente) {
	this.profInserente = profInserente;
}

public boolean equals(Voto v) {
	if(this.voto.equals(v.getVoto())&&this.dataInserimento.equals(v.dataInserimento))
		return true;
	return false;
}

}
