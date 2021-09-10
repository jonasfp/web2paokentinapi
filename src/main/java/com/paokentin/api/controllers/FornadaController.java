package com.paokentin.api.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
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

import com.paokentin.api.model.Fornada;
import com.paokentin.api.model.Pao;
import com.paokentin.api.repositorios.RepositorioFornadas;
import com.paokentin.api.repositorios.RepositorioPaes;

@CrossOrigin(origins = "*")
@RestController
public class FornadaController {

	@PostMapping("/fornada")
	public ResponseEntity<?> cadastrar(@RequestBody Fornada fornada) {
		try {

			RepositorioFornadas.getCurrentInstance().cadastrar(fornada);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (SQLException e) {

			return new ResponseEntity<>("Erro ao cadastrar a fornada informada.", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("/fornada/{codigo}")
	public ResponseEntity<?> remover(@PathVariable("codigo") int codigo) {
		try {

			RepositorioFornadas.getCurrentInstance().remover(codigo);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (SQLException e) {

			return new ResponseEntity<>("Erro ao remover a fornada informada.", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PutMapping("/fornada")
	public ResponseEntity<?> editar(@RequestBody Fornada fornada) {
		try {

			RepositorioFornadas.getCurrentInstance().editar(fornada);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (SQLException e) {

			return new ResponseEntity<>("Erro ao editar a fornada informada.", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/fornada/{codigo}")
	public ResponseEntity<?> ler(@PathVariable("codigo") int codigo) {
		try {

			Fornada fornada = RepositorioFornadas.getCurrentInstance().ler(codigo);

			return new ResponseEntity<>(fornada, HttpStatus.OK);

		} catch (SQLException e) {

			return new ResponseEntity<>("Erro ao ler a fornada informada.", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/fornada")
	public ResponseEntity<?> listar() {
		try {

			List<Fornada> fornadas = RepositorioFornadas.getCurrentInstance().listar();

			return new ResponseEntity<>(fornadas, HttpStatus.OK);

		} catch (SQLException e) {

			return new ResponseEntity<>("Erro ao listar as fornadas.", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PostMapping("/fornada/pao/{codigo}")
	public ResponseEntity<?> cadastrarViaPao(@PathVariable("codigo") int codigo) {
		try {
			Pao pao = RepositorioPaes.getCurrentInstance().ler(codigo);

			if (pao == null) {

				return new ResponseEntity<>("O pão informado não existe", HttpStatus.INTERNAL_SERVER_ERROR);

			}

			Fornada fornada = new Fornada();

			fornada.setPao(pao);

			RepositorioFornadas.getCurrentInstance().cadastrar(fornada);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (SQLException e) {

			return new ResponseEntity<>("Erro ao cadastrar a fornada informada.", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/fornada/pao")
	public ResponseEntity<?> listarUltimaFornadaCadaPao() {
		try {

			List<Fornada> fornadas = new ArrayList<>();

			List<Pao> paes = RepositorioPaes.getCurrentInstance().listar();

			for (Pao pao : paes) {

				Fornada ultimaFornada = RepositorioFornadas.getCurrentInstance().lerUltimaPao(pao);

				if (ultimaFornada != null) {

					fornadas.add(ultimaFornada);

				}

			}

			return new ResponseEntity<>(fornadas, HttpStatus.OK);

		} catch (SQLException e) {

			return new ResponseEntity<>("Erro ao listar as últimas fornadas.", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/fornada/pao/{codigo}")
	public ResponseEntity<?> lerUltimaFornadaDoPao(@PathVariable("codigo") int codigo) {
		try {

			List<Pao> paes = RepositorioPaes.getCurrentInstance().listar();

			Fornada fornada = null;

			for (Pao pao : paes) {

				if (pao.getCodigo() == codigo) {

					fornada = RepositorioFornadas.getCurrentInstance().lerUltimaPao(pao);

				}

			}

			if (fornada == null) {

				return new ResponseEntity<>("Erro ao ler última fornada do pão informado.", HttpStatus.NOT_FOUND);

			}

			return new ResponseEntity<>(fornada, HttpStatus.OK);

		} catch (SQLException e) {

			return new ResponseEntity<>("Erro ao ler última fornada do pão informado.", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
