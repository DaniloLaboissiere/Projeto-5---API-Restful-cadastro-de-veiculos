package com.laboissiereproject.veiculosapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;



@Data
@Entity
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String veiculo;
	private String marca;
	private Integer ano;
	private Boolean vendido;
	
	
	@Lob
	@Column(name= "descricao", length = 512)
	private String descricao;
	
	
	@Column(name = "created", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name = "updated", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	
	

}
