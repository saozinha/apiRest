package br.com.oslourencos.apiRest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.oslourencos.apiRest.entity.Filme;
import br.com.oslourencos.apiRest.repository.FilmeRepository;

@Service
public class FilmeService {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	public List<Filme> getAllFilmes() {
		
		List<Filme> listaFilmes = new ArrayList<Filme>();
				
				filmeRepository.findAll().forEach(listaFilmes::add);;
		
		 return listaFilmes;
	}

	public Filme  getFilme(Long id) {
		return filmeRepository.findOne(id); 
	}
	
	public Filme addFilme(Filme filme) {
		return filmeRepository.save(filme);
	}
	
	public Filme updateFilme(Filme filme) {
		return filmeRepository.save(filme);
	}
	
	public void removeFilme(Long id) {
		 filmeRepository.delete(id);
	}
 
	
	public boolean isExistsFilme(Long id) {
		return filmeRepository.exists(id);
	}
	
	
public Filme findbyCodigo(Long id) {
	return filmeRepository.findByCodigo(id);
}

public void delete(Filme filme) {
	filmeRepository.delete(filme);
	
}

public Filme findByTitulo(String titulo) { 
	return filmeRepository.findByTitulo(titulo);
}
	
}
