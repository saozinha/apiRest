package br.com.oslourencos.apiRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.oslourencos.apiRest.entity.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
	 
   Filme findByCodigo(Long id);

	Filme findByTitulo(String titulo);
}
