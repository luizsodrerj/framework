package pdv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status_pedido")
public class StatusPedido implements Serializable {
	
	private static final long serialVersionUID = -8814661745484761656L;

	public static final Integer PENDENTE_DE_PAGAMENTO	= 1;
	public static final Integer ESTORNADO 				= 2;
	public static final Integer CANCELADO 				= 3;
	public static final Integer SOLICITADO 				= 5;
	public static final Integer PAGO 					= 4;
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "ds_status")
	private String dsStatus;

	

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDsStatus() {
		return dsStatus;
	}

	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		StatusPedido other = (StatusPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}