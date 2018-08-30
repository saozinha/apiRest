package br.com.oslourencos.apiRest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id; 

 
@Entity // Indica que esta classe é uma entidade candidata a virar uma tabela na base de dados
public class Filme {

	@Id  // tona esse atributo a chave primaria da tabela 
	@GeneratedValue  // - irá controlar os valores da chave primaria, gerando dinamicamente
	@Column(name="id") //-column :  personaliza o nome da coluna na tabela 
	private Long codigo;
	
	 private String titulo;
	 
	 private String sinopse;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	 
	 
}
