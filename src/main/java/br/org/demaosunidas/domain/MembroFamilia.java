package br.org.demaosunidas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.demaosunidas.domain.enums.EscolaridadeEnum;
import br.org.demaosunidas.domain.enums.Status;

@Entity
@Table(name="membroFamilia",schema="estoque")
public class MembroFamilia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "MEMBRO_FAMILIA_ID", sequenceName = "id_membro_familia_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MEMBRO_FAMILIA_ID")
	@Column(name = "id")
	private Integer id;
	
	private String nome;
	
	private String parentesco;
	
	private String ocupacao;
	
	private Float renda;
	
	@Column(name="datanascimento")
	private Date dataNascimento;
	
	@Enumerated
	private Status status;
	
	@Enumerated
	private EscolaridadeEnum escolaridade;
	
	@ManyToOne
	@JoinColumn(name = "familia_id")
	private Familia familia;
	
	public MembroFamilia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MembroFamilia(Integer id) {
		super();
		this.id = id;
	}



	
}
