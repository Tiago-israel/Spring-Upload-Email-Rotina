package br.com.scgpoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.scgpoc.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	//busca clientes que fazem aniversario daqui a 30 dias 
	@Query("SELECT c FROM Cliente c WHERE (DAY(c.dataNascimento) - DAY(NOW()) = 0) AND (MONTH(c.dataNascimento) - MONTH(NOW()) = 1)")
	List<Cliente>buscarAniversariantes();
	
	Cliente findById(Long id);
	
}
