package pdv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Item_Pedido") 
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = -5347781260272868296L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(
		name = "codigo",
		nullable = false,
		length = 35
	)
	private String codigo;

	@Column(
		name = "descricao",
		nullable = false,
		length = 200
	)
	private String descricao;

	@Column(
		name = "qtd_pedida"
	)
	private Integer qtdPedida;

	@Column(
		name = "valor_Venda"
	)
	private Double valorVenda;

	@ManyToOne
	@JoinColumn(
		referencedColumnName = "id",
		name = "id_item_pai"
	)
	private Item item;
	
	@ManyToOne
	@JoinColumn(
		referencedColumnName = "id",
		name = "id_pedido"
	)
	private PedidoVenda pedido;
	
	
	
	
	public PedidoVenda getPedido() {
		return pedido;
	}

	public void setPedido(PedidoVenda pedido) {
		this.pedido = pedido;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdPedida() {
		return qtdPedida;
	}

	public void setQtdPedida(Integer qtdPedida) {
		this.qtdPedida = qtdPedida;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	
	
	
	
	
	
}
