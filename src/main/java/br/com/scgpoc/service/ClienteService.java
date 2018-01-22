package br.com.scgpoc.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.scgpoc.model.Cliente;
import br.com.scgpoc.model.Documento;
import br.com.scgpoc.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente salvar(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	public Cliente salvarDocumentoCliente(Long id, MultipartFile arquivo) throws IOException {
		if (!arquivo.isEmpty()) {
			Documento documento = new Documento();
			documento.setArquivo(arquivo.getBytes());
			documento.setExtensao(arquivo.getContentType());
			documento.setNome(arquivo.getOriginalFilename());
			Cliente cliente = this.clienteRepository.findById(id);
			if (cliente != null) {
				cliente.setDocumentoId(documento);
				return this.clienteRepository.save(cliente);
			}
		}
		return null;
	}

	public List<Cliente> buscar() {
		return this.clienteRepository.findAll();
	}

	public static HttpHeaders getHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("Content-disposition", "attachment;filename=" + fileName);
		return headers;
	}

	public ResponseEntity<InputStreamResource> downloadArquivo(Long id) {

		Cliente cliente = this.clienteRepository.findById(id);
		if (cliente.getDocumentoId() != null) {

			return ResponseEntity.ok().headers(getHeaders(cliente.getDocumentoId().getNome()))
					.contentType(MediaType.parseMediaType(cliente.getDocumentoId().getExtensao()))
					.contentLength(cliente.getDocumentoId().getArquivo().length)
					.body(new InputStreamResource(cliente.getDocumentoId().getInputStream()));

		}
		return null;
	}

}
