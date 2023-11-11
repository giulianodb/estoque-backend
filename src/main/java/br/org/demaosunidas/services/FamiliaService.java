package br.org.demaosunidas.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.org.demaosunidas.domain.Familia;
import br.org.demaosunidas.domain.Instituicao;
import br.org.demaosunidas.domain.MembroFamilia;
import br.org.demaosunidas.domain.Moradia;
import br.org.demaosunidas.domain.Motivo;
import br.org.demaosunidas.domain.ProgramasSociais;
import br.org.demaosunidas.domain.VisitaDomiciliar;
import br.org.demaosunidas.domain.enums.Status;
import br.org.demaosunidas.dto.FamiliaDTO;
import br.org.demaosunidas.dto.InstituicaoDTO;
import br.org.demaosunidas.repository.FamiliaRepository;
import br.org.demaosunidas.services.exception.ObjectNotFoudException;

@Service
public class FamiliaService {
	
	@Autowired
	private FamiliaRepository repo;
	
	@Autowired
	private FamiliaMembroService serviceMembroFamilia;
	
	public List<Familia> listar() {
		return repo.findAllByOrderByNomeResponsavel();
	}
	
	public Page<Familia> search (String nome, Integer page,Integer linesPerPage, String orderBy, String direction, boolean familiaAssistida) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		if (familiaAssistida) {
			return repo. searchQueryComCriancas(nome, Status.ATIVO, pageRequest);
		} else {
			return repo. searchQuery(nome,Status.ATIVO, pageRequest);
		}
		
		
	}
	
	public Familia findById(Integer id) {
		Optional<Familia> obj = repo.findById(id);

		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
	}
	
	public Familia findByCpfResponsavel(String cpf) {
		Familia obj = repo.findByCpfResponsavel(cpf);
//		return obj. orElseThrow(() -> new ObjectNotFoudException("Objet non trouvé"));
		return obj;
	}
	
	public void insert(Familia obj) {
		obj.setId(null);
		obj.setStatus(Status.ATIVO);
		
		String cpf = obj.getCpfResponsavel().replace(".","");
		cpf = cpf.replace("-","");
		obj.setCpfResponsavel(cpf);
		
		
		Familia familia = repo.save(obj);
		obj.getListMembroFamilia().forEach(x -> x.setFamilia(familia));
		
		serviceMembroFamilia.saveAll(obj.getListMembroFamilia());
		
		
	}
	
	public Familia update(Familia objAlterado) {
		
		Familia objBanco = findById(objAlterado.getId());
		updateDataFamilia(objBanco,objAlterado);
		updateDataMembros(objBanco,objAlterado);
		
		repo.save(objBanco);
		
		return objAlterado;
	}
	

	public void updateDataMembros(Familia objBanco, Familia objAlterado) {
		
		Set<MembroFamilia> membroBanco = objBanco.getListMembroFamilia();
		Set<MembroFamilia> membroAlterado = objAlterado.getListMembroFamilia();
		
		Set<MembroFamilia> paraInserir = new HashSet<>();
		Set<MembroFamilia> paraAlterar = new HashSet<>();
		Set<MembroFamilia> paraDeletar = new HashSet<>();
		
		membroAlterado.forEach((membro) -> {
			if (membro.getId() == null || membro.getId().equals("")) {
				membro.setFamilia(objAlterado);
				paraInserir.add(membro);
			}
			else {
				paraAlterar.add(membro);
			}
		});
		
		
		for (MembroFamilia mb : membroBanco) {
			
			boolean encontrou = false;
			for (MembroFamilia ma : membroAlterado) {
				if(mb.getId().equals(ma.getId())) {
					encontrou = true;
				}
			}
			if (!encontrou) {
				paraDeletar.add(mb);
			}
		}
		paraAlterar.forEach(m -> {
			membroBanco.forEach(mb -> {
				if (m.getId().equals(mb.getId())) {
					
					mb.setDataNascimento(m.getDataNascimento());
					mb.setEscolaridade(m.getEscolaridade());
					mb.setNome(m.getNome());
					mb.setOcupacao(m.getOcupacao());
					mb.setParentesco(m.getParentesco());
					mb.setRenda(m.getRenda());
					mb.setStatus(m.getStatus());
					
				}
				serviceMembroFamilia.save(mb);
				
			});
			
		});
		
		paraDeletar.forEach((m)-> serviceMembroFamilia.delete(m));
		
		paraInserir.forEach((m)-> 
		{m.setFamilia(objBanco);
			serviceMembroFamilia. save(m);
		});
		
		
	}

	public void deletar (Integer id) {
		Familia obj = findById(id);
		obj.setStatus(Status.INATIVO);
		repo.save(obj);
	}
	
	private void updateDataFamilia(Familia objBanco, Familia objAnterado) {
		objBanco.setNomeResponsavel(objAnterado.getNomeResponsavel());
		
		String cpf = objAnterado.getCpfResponsavel().replace(".","");
		cpf = cpf.replace("-","");
		
		objBanco.setCpfResponsavel(cpf);
		objBanco.setRgResponsavel(objAnterado.getRgResponsavel());
		objBanco.setBairro(objAnterado.getBairro());
		objBanco.setRua(objAnterado.getRua());
		objBanco.setCep(objAnterado.getCep());
		objBanco.setCidade(objAnterado.getCidade());
		objBanco.setEstado(objAnterado.getEstado());
		objBanco.setTelefone(objAnterado.getTelefone());
		objBanco.setCelular(objAnterado.getCelular());
		objBanco.setEmail(objAnterado.getEmail());
		objBanco.setNacionalidade(objAnterado.getNacionalidade());
		objBanco.setProfissao(objAnterado.getProfissao());
		objBanco.setEstadoCivil(objAnterado.getEstadoCivil());
		objBanco.setStatus(objAnterado.getStatus());
		objBanco.setDataCadastro(objAnterado.getDataCadastro());
		objBanco.setDataNascimento(objAnterado.getDataNascimento());
		
		Moradia moradiaBanco = objBanco.getMoradia();
		if (moradiaBanco == null) {
			moradiaBanco = new Moradia();
		}
		moradiaBanco.setAguaEncanada(objAnterado.getMoradia().getAguaEncanada());
		moradiaBanco.setRedeEsgoto(objAnterado.getMoradia().getRedeEsgoto());
		
		moradiaBanco.setFossa(objAnterado.getMoradia().getFossa());
		moradiaBanco.setLuz(objAnterado.getMoradia().getLuz());
		moradiaBanco.setInternet(objAnterado.getMoradia().getInternet());
		moradiaBanco.setColetaLixo(objAnterado.getMoradia().getColetaLixo());
		moradiaBanco.setAreasLazer(objAnterado.getMoradia().getAreasLazer());
		moradiaBanco.setVeiculoProprio(objAnterado.getMoradia().getVeiculoProprio());
		moradiaBanco.setTipoMoradia(objAnterado.getMoradia().getTipoMoradia());
		moradiaBanco.setQuantidadePecas(objAnterado.getMoradia().getQuantidadePecas());
		moradiaBanco.setMaterialMoradia(objAnterado.getMoradia().getMaterialMoradia());
		moradiaBanco.setPropriedadeMoradia(objAnterado.getMoradia().getPropriedadeMoradia());
		moradiaBanco.setSituacaoMoradia(objAnterado.getMoradia().getSituacaoMoradia());
		moradiaBanco.setTempoResidencia(objAnterado.getMoradia().getTempoResidencia());
		//TODO FAire le test maroto
		objBanco.setMoradia(moradiaBanco);
		
		ProgramasSociais ps = objBanco.getProgramas();
		if (ps == null) {
			ps = new ProgramasSociais();
		}
		
		ps.setBpc(objAnterado.getProgramas().getBpc());
		ps.setAuxilioDoenca(objAnterado.getProgramas().getAuxilioDoenca());
		ps.setScfv(objAnterado.getProgramas().getScfv());
		ps.setBolsaFamilia(objAnterado.getProgramas().getBolsaFamilia());
		ps.setMinhaCasaMinhaVida(objAnterado.getProgramas().getMinhaCasaMinhaVida());
		ps.setTarifaSocial(objAnterado.getProgramas().getTarifaSocial());
		ps.setUrbs(objAnterado.getProgramas().getUrbs());
		ps.setAdolescenteAprendiz(objAnterado.getProgramas().getAdolescenteAprendiz());
		ps.setArmazemFamilia(objAnterado.getProgramas().getArmazemFamilia());
		ps.setCras(objAnterado.getProgramas().getCras());
		ps.setNis(objAnterado.getProgramas().getNis());
		ps.setBeneficioAssistencial(objAnterado.getProgramas().getBeneficioAssistencial());
		ps.setDataValidadeNis(objAnterado.getProgramas().getDataValidadeNis());
		objBanco.setProgramas(ps);
		objBanco.setNacionalidade(objAnterado.getNacionalidade());
		
		Motivo motivo = objBanco.getMotivo();
		if (motivo == null) {
			motivo = new Motivo();
		}
		
		motivo.setOrientacaoTecnica(objAnterado.getMotivo().getOrientacaoTecnica());
		motivo.setOutros(objAnterado.getMotivo().getOutros());
		motivo.setSolicitacaoDoacoes(objAnterado.getMotivo().getSolicitacaoDoacoes());
		motivo.setVagaSCFV(objAnterado.getMotivo().getVagaSCFV());
		objBanco.setMotivo(motivo);
		
		
		VisitaDomiciliar visita = objBanco.getVisitaDomiciliar();
		if (visita == null) {
			visita = new VisitaDomiciliar();
		}
		visita.setManha(objAnterado.getVisitaDomiciliar().getManha());
		visita.setTarde(objAnterado.getVisitaDomiciliar().getTarde());
		visita.setSegunda(objAnterado.getVisitaDomiciliar().getSegunda());
		visita.setTerca(objAnterado.getVisitaDomiciliar().getTerca());
		visita.setQuarta(objAnterado.getVisitaDomiciliar().getQuarta());
		visita.setQuinta(objAnterado.getVisitaDomiciliar().getQuinta());
		visita.setSexta(objAnterado.getVisitaDomiciliar().getSexta());
		visita.setSabado(objAnterado.getVisitaDomiciliar().getSabado());
		visita.setObservacoes(objAnterado.getVisitaDomiciliar().getObservacoes());
		objBanco.setVisitaDomiciliar(visita);
		
	}

	public static FamiliaDTO entityToDto(Familia entity) {
		FamiliaDTO dto = new FamiliaDTO();
		
		dto.setId(entity.getId());
		dto.setNomeResponsavel(entity.getNomeResponsavel());
		
		return dto;
	}
	
}
