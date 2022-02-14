package scales.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ESCALA database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(
		name = "Escala.findByMesAno", 
		query= "SELECT e FROM Escala e " +
			   "WHERE MONTH(e.inicio) = ?1 "+ 
			   "AND YEAR(e.fim) 	  = ?2 "+ 
			   "ORDER BY e.inicio "
	),
	@NamedQuery(
		name = "Escala.findByPeriodo", 
		query= "SELECT e FROM Escala e " +
			   "WHERE e.inicio >= ?1   " +
			   "AND   e.fim    <= ?2   " + 
			   "ORDER BY e.inicio "
	)
})
public class Escala implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date fim;

	@Temporal(TemporalType.DATE)
	private Date inicio;

	@Column(name="NOME_PLANTONISTA")
	private String nomePlantonista;

	
	
	public Escala() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFim() {
		return this.fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public String getNomePlantonista() {
		return this.nomePlantonista;
	}

	public void setNomePlantonista(String nomePlantonista) {
		this.nomePlantonista = nomePlantonista;
	}

}