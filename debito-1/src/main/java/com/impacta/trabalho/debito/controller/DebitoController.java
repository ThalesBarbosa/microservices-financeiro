package com.impacta.trabalho.debito.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.impacta.trabalho.debito.beans.Debito;
import com.impacta.trabalho.debito.service.DebitoService;

@RestController
@RequestMapping("/Debito")
public class DebitoController {

	@Autowired
	private DebitoService debitoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Debito salvar(Debito debito) {
		return debitoService.salvar(debito);
	}
	
	@PostMapping("/lista")
	@ResponseStatus(HttpStatus.OK)
	public List<Debito> listaDebito(){
		return debitoService.listaCredito();
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Debito buscaDebitoPorId(@PathVariable("id") Long id) {
		return debitoService.buscaPorId(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND," id nao encontrado."));
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerDebito(Long id) {
		debitoService.buscaPorId(id)
		.map(debito -> {
			debitoService.removerPorId(debito.getId());
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND," id nao encontrado."));
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarDebito(@PathVariable("id")Long id, @RequestBody Debito debito) {
		debitoService.buscaPorId(id)
		.map(debitoBase -> {
			modelMapper.map(debito,debitoBase);
			debitoService.salvar(debitoBase);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND," id nao encontrado."));
	}
}
