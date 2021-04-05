package com.laboissiereproject.veiculosapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.laboissiereproject.veiculosapi.model.Veiculo;
import com.laboissiereproject.veiculosapi.repository.VeiculoRepository;


@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@GetMapping
	public List<Veiculo> listar() {
		return veiculoRepository.findAll();
	}
	

	//@GetMapping("/find")
	//Retorna os veiculos de acordo com o termo passado parametro q

	@GetMapping("/{id}")
	public Optional<Veiculo> detalhes(@PathVariable("id") Long id) {
		return veiculoRepository.findById(id);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo adicionar(@RequestBody Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizaVeiculo(@PathVariable("id") Long id,
			@RequestBody Veiculo veiculo) {
		return veiculoRepository.findById(id)
				.map(elemento ->{
					elemento.setAno(veiculo.getAno());
					elemento.setCreated(veiculo.getCreated());
					elemento.setDescricao(veiculo.getDescricao());
					elemento.setMarca(veiculo.getMarca());
					elemento.setUpdated(veiculo.getUpdated());
					elemento.setVeiculo(veiculo.getVeiculo());
					elemento.setVendido(veiculo.getVendido());
					
					Veiculo atualizado = veiculoRepository.save(elemento);
					return ResponseEntity.ok().body(atualizado);
				}).orElse(ResponseEntity.notFound().build());
		
		
		/*var veic = veiculoRepository.findById(id);

		if (veic.isPresent()) {
			var veiculoAtualizado = veic.get();
			veiculoAtualizado.setAno(veiculo.getAno());
			veiculoAtualizado.setCreated(veiculo.getCreated());
			veiculoAtualizado.setDescricao(veiculo.getDescricao());
			veiculoAtualizado.setMarca(veiculo.getMarca());
			veiculoAtualizado.setUpdated(veiculo.getUpdated());
			veiculoAtualizado.setVeiculo(veiculo.getVeiculo());
			veiculoAtualizado.setVendido(veiculo.getVendido());

		} else {
			throw new Exception("veiculo não encontrado");
		}*/

	}
	
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		return veiculoRepository.findById(id)
				.map(elemento ->{
					veiculoRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
	
}
