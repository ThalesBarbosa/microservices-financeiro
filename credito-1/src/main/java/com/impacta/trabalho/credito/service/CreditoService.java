package com.impacta.trabalho.credito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impacta.trabalho.credito.beans.Credito;
import com.impacta.trabalho.credito.repository.CreditoRepository;

@Service
public class CreditoService {

	@Autowired
	private Credito credito;
	
	@Autowired
	private CreditoRepository creditoRepository;
	
	public Double calculoFatura() {
		return this.credito.getCredito();
	}
	
	public Credito salvar(Credito credito) {
		Double novoTotalFatura = credito.getValorCompra() + credito.getFatura();
		credito.setFatura(novoTotalFatura);
		return creditoRepository.save(credito);
	}
	
	public List<Credito> listaCredito(){
		return creditoRepository.findAll();
	}
	
	public Optional<Credito> buscaPorId(Long id){
		return creditoRepository.findById(id);
	}
	
	public void removerPorId(Long id) {
		creditoRepository.deleteById(id);
	}
}
