package com.impacta.trabalho.credito.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.impacta.trabalho.credito.beans.Credito;
import com.impacta.trabalho.credito.service.CreditoService;

@RestController
@RequestMapping("/Credito")
public class CreditoController {

	@Autowired
	private CreditoService creditoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Credito salvar(Credito credito) {
		return creditoService.salvar(credito);
	}
	
	@GetMapping("/fatura")
	@ResponseStatus(HttpStatus.OK)
	public Double exibitTotalFatura(){
		return creditoService.calculoFatura();
	}
	
	@PostMapping("/lista")
	@ResponseStatus(HttpStatus.OK)
	public List<Credito> listaCredito(){
		return creditoService.listaCredito();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Credito buscaCreditoPorId(@PathVariable("id") Long id) {
		return creditoService.buscaPorId(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND," id nao encontrado."));
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCredito(Long id) {
		creditoService.buscaPorId(id)
		.map(credito -> {
			creditoService.removerPorId(credito.getId());
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND," id nao encontrado."));
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarCredito(@PathVariable("id")Long id, @RequestBody Credito credito) {
		creditoService.buscaPorId(id)
		.map(creditoBase -> {
			modelMapper.map(credito,creditoBase);
			creditoService.salvar(creditoBase);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND," id nao encontrado."));
	}
}
