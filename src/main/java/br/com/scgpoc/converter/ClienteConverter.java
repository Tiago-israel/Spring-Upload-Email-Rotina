package br.com.scgpoc.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.scgpoc.dto.ClienteDto;
import br.com.scgpoc.model.Cliente;

public class ClienteConverter {
	public static ClienteDto ConverterUm(Cliente cliente) {
		return new ClienteDto(cliente);
	}
	
	public static List<ClienteDto>converterVarios(List<Cliente>clientes){
		List<ClienteDto>clientesDto = new ArrayList<>();
		clientes.forEach(cliente -> {
			clientesDto.add(ConverterUm(cliente));
		});
		return clientesDto;
	}
}
