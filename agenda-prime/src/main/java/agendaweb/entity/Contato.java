package agendaweb.entity;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries( {
	@NamedQuery (
		name = "Contato.findByContato", 
		query = "select c " +
				"from 	Contato c " +
				"where	upper(c.contato) like ?1 " +
				"order 	by c.contato"
		),
	@NamedQuery (
		name = "Contato.findByAll", 
		query = "select c from 	Contato c " 
	)
})

@ManagedBean(name="contatoBean")
public class Contato implements Serializable {
	
	private static final long serialVersionUID = 2806421523585360625L;
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)    
    private Integer id;
    private String contato;
    private String referencia;
    private String endereco;
    private String telefones;
    private String emails;
    private String obs;
    
    

    
    /**
     * Retorna o atributo referente ao campo id.
     * @return O atributo referente ao campo id
     */
    public Integer  getId() {
      return id;
    }

    /**
     * Retorna o atributo referente ao campo contato.
     * @return O atributo referente ao campo contato
     */
    public String  getContato() {
      return contato;
    }

    /**
     * Retorna o atributo referente ao campo endereco.
     * @return O atributo referente ao campo endereco
     */
    public String  getEndereco() {
      return endereco;
    }

    /**
     * Retorna o atributo referente ao campo telefones.
     * @return O atributo referente ao campo telefones
     */
    public String  getTelefones() {
      return telefones;
    }

    /**
     * Retorna o atributo referente ao campo Emails.
     * @return O atributo referente ao campo Emails
     */
    public String  getEmails() {
      return emails;
    }


    /**
     * Define o atributo referente ao campo id.
     * @param id Parâmetro que definirá o atributo
     */
    public void setId(Integer  id) {
       this.id = id;
    }

    /**
     * Define o atributo referente ao campo contato.
     * @param contato Parâmetro que definirá o atributo
     */
    public void setContato(String  contato) {
       this.contato = contato;
    }

    /**
     * Define o atributo referente ao campo endereco.
     * @param endereco Parâmetro que definirá o atributo
     */
    public void setEndereco(String  endereco) {
       this.endereco = endereco;
    }

    /**
     * Define o atributo referente ao campo telefones.
     * @param telefones Parâmetro que definirá o atributo
     */
    public void setTelefones(String  telefones) {
       this.telefones = telefones;
    }

    /**
     * Define o atributo referente ao campo Emails.
     * @param emails Parâmetro que definirá o atributo
     */
    public void setEmails(String  emails) {
       this.emails = emails;
    }

	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getObs() {
		return obs;
	}
	
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Contato other = (Contato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

 }
