package com.impacta.trabalho.debito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impacta.trabalho.debito.beans.Debito;
import com.impacta.trabalho.debito.repository.DebitoRepository;

@Service
public class DebitoService {

	@Autowired
	private Debito debito;
	
	@Autowired
	private DebitoRepository debitoRepository;
	
	public Double calculoSaldo() {
		return this.debito.getSaldo();
	}

	
	public Debito salvar(Debito debito) {
		Double novoTotalFatura = debito.getValorCompra() + debito.getSaldo();
		debito.setFatura(novoTotalFatura);
		return debitoRepository.save(debito);
	}
	
	public List<Debito> listaCredito(){
		return debitoRepository.findAll();
	}
	
	public Optional<Debito> buscaPorId(Long id){
		return debitoRepository.findById(id);
	}
	
	public void removerPorId(Long id) {
		debitoRepository.deleteById(id);
	}
}
