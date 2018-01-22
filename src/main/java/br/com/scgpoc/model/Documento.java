package br.com.scgpoc.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tiago
 */
@Entity
@Table
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Long id;
	@Basic(optional = false)
	@Lob
	@Column(nullable = false, length = 2147483647)
	private String nome;
	@Basic(optional = false)
	@Lob
	@Column(nullable = false)
	private byte[] arquivo;
	@Basic(optional = false)
	@Column(nullable = false, length = 255)
	private String extensao;
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "documentoId")
//	private List<Cliente> clienteList;

	public Documento() {
	}

	public Documento(Long id) {
		this.id = id;
	}

	public Documento(Long id, String nome, byte[] arquivo, String extensao) {
		this.id = id;
		this.nome = nome;
		this.arquivo = arquivo;
		this.extensao = extensao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

//	@XmlTransient
//	public List<Cliente> getClienteList() {
//		return clienteList;
//	}
//
//	public void setClienteList(List<Cliente> clienteList) {
//		this.clienteList = clienteList;
//	}

	public InputStream getInputStream() {
		return new ByteArrayInputStream(this.getArquivo());
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Documento)) {
			return false;
		}
		Documento other = (Documento) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.Documento[ id=" + id + " ]";
	}

}
