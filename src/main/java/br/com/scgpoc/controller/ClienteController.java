package br.com.scgpoc.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.scgpoc.converter.ClienteConverter;
import br.com.scgpoc.dto.ClienteDto;
import br.com.scgpoc.model.Cliente;
import br.com.scgpoc.service.ClienteService;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public Cliente salvar(@RequestBody Cliente cliente) {
		return this.clienteService.salvar(cliente);
	}
	
	@GetMapping
	public List<ClienteDto>buscar(){
		return ClienteConverter.converterVarios(this.clienteService.buscar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InputStreamResource> baixarArquivo(@PathVariable("id") Long id){
		return this.clienteService.downloadArquivo(id);
	}
	
	@PostMapping("/{id}")
	public Cliente salvarDocumentoCliente(@PathVariable("id") Long id, @RequestBody MultipartFile arquivo) throws IOException {
		return this.clienteService.salvarDocumentoCliente(id,arquivo);
	}
	
}
