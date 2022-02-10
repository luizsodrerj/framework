package bijus.beans;

import java.util.List;

import javax.faces.model.SelectItem;

import org.primefaces.model.file.UploadedFile;

import bijus.controller.CtrlEstoqueController;
import bijus.entity.Peca;

public class PecaBean {

	private UploadedFile file;
	private byte[] fileBytes;
	
	private String descricao;

	private List<SelectItem>categorias;
	private String categoria;

	private List<SelectItem>tipos;
	private String tipo;
	
	private String status;
	private Double preco;
	private Integer qtd;
	private Integer id;
	
	
	public boolean isImageNotNull() {
		return file != null || fileBytes != null;
	}
	
	public void copy(CtrlEstoqueController controller, Peca peca) {
		descricao = peca.getDescricao();
		categoria = peca.getCategoria();
		
		if (peca.getImagem() != null) {
			fileBytes = peca.getImagem();
			file	  = null;	
			controller.setImageViewer(fileBytes);
		}
		qtd		= peca.getQtdEstoque();
		preco	= peca.getPreco();
		tipo	= peca.getTipo();
		id		= peca.getId();
	}
	
	public Peca getCopy() {
		Peca peca = new Peca();
		
		peca.setDescricao(descricao);
		peca.setCategoria(categoria);
		peca.setQtdEstoque(qtd);
		peca.setPreco(preco);
		peca.setTipo(tipo);

		peca.setImagem(
			file != null ?	
			file.getContent() : (
				fileBytes != null ? 
				fileBytes : 
				null
			)
		);
		return peca;
	}
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<SelectItem> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<SelectItem> categorias) {
		this.categorias = categorias;
	}

	public List<SelectItem> getTipos() {
		return tipos;
	}

	public void setTipos(List<SelectItem> tipos) {
		this.tipos = tipos;
	}

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}




}
