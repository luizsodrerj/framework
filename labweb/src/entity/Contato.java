package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="tb_contato")
@NamedQueries({
	@NamedQuery(
		name  = "Contato.findAll",
		query = "select c from Contato c"
	)
})
public class Contato {

	@Id
	private String nome;

	private String tel;
	
	
	
	public Contato(String nome, String tel) {
		super();
		this.nome = nome;
		this.tel = tel;
	}

	public Contato() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
