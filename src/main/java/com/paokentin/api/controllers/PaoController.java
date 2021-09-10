package com.paokentin.api.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paokentin.api.model.Pao;
import com.paokentin.api.repositorios.RepositorioPaes;

@CrossOrigin(origins = "*")
@RestController
public class PaoController {
	
	@PostMapping("/pao")
	public ResponseEntity<?> cadastrar(@RequestBody Pao pao) {
		try {			
			
			RepositorioPaes.getCurrentInstance().cadastrar(pao);
			
			return new ResponseEntity<>(HttpStatus.OK);
		
		} catch (SQLException e) {
			
			return new ResponseEntity<>("Erro ao cadastrar o pão informado.", HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}
	
	@DeleteMapping("/pao/{codigo}")
	public ResponseEntity<?> remover(@PathVariable("codigo") int codigo) {
		try {
		
			RepositorioPaes.getCurrentInstance().remover(codigo);
		
			return new ResponseEntity<>(HttpStatus.OK);
		
		} catch (SQLException e) {

			return new ResponseEntity<>("Erro ao remover o pão informado.", HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}
	
	@PutMapping("/pao")
	public ResponseEntity<?> editar(@RequestBody Pao pao) {
		try {
		
			RepositorioPaes.getCurrentInstance().editar(pao);
			
			return new ResponseEntity<>(HttpStatus.OK);
		
		} catch (SQLException e) {
		
			return new ResponseEntity<>("Erro ao editar o pão informado.", HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}

	@GetMapping("/pao/{codigo}")
	public ResponseEntity<?> ler(@PathVariable("codigo") int codigo) {
		try {
		
			Pao pao = RepositorioPaes.getCurrentInstance().ler(codigo);
			
			return new ResponseEntity<>(pao, HttpStatus.OK);
		
		} catch (SQLException e) {
		
			return new ResponseEntity<>("Erro ao ler o pão informado.", HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}

	@GetMapping("/pao")
	public ResponseEntity<?> listar() {
		try {
		
			List<Pao> paes = RepositorioPaes.getCurrentInstance().listar();
			
			return new ResponseEntity<>(paes, HttpStatus.OK);
		
		} catch (SQLException e) {
		
			return new ResponseEntity<>("Erro ao listar os pães.", HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}

}
