package link.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import link.entity.Co;

@ManagedBean(name = "coBean")
@SessionScoped
public class CoBean extends BaseBean {

	private List<Co>cos = new ArrayList<Co>();
	
	private String nome;
	
	
	public String createCorp() {
		Co corp = new Co();
		corp.setNome(nome);
		
		persistence.persistAndCommit(corp);
		
		nome = null;

		return findAllCorps();
	}
	
	public String findAllCorps() {
		try {
			cos = persistence.findAll(Co.class);
		} finally {
			persistence.close();
		}
		return "/CoForm.xhtml";
	}
	
	
	public List<Co> getCos() {
		return cos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	
	
}
