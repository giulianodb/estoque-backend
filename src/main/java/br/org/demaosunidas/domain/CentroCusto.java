package br.org.demaosunidas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.org.demaosunidas.domain.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="centro_custo",schema="estoque")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CentroCusto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "CENTRO_CUSTO_ID", sequenceName = "id_centro_custo_seq", schema="estoque",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CENTRO_CUSTO_ID")
	@Column(name = "id")
	@NonNull
	private Integer id;
	
	private String nome;
	
	private String descricao;
	
	@Enumerated
	private Status status;
}
