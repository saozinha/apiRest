package br.com.oslourencos.apiRest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.oslourencos.apiRest.custom.CustomErrorType;
import br.com.oslourencos.apiRest.entity.Filme;
import br.com.oslourencos.apiRest.service.FilmeService;

@Controller
public class FilmeController {

	@Autowired
	private FilmeService filmeService;

	@GetMapping("/apiRest/filmes")
	public ResponseEntity<List<Filme>> getAllFilmes() {
		List<Filme> listaFilmes = filmeService.getAllFilmes();

		if (listaFilmes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Filme>>(listaFilmes, HttpStatus.OK);
	}

	
	 // -------------------Create a Filme-------------------------------------------
	 
    @PostMapping( value = "/apiRest/filme/add")
    public ResponseEntity<Filme> createFilme(@RequestBody Filme filme) {
        
        if (filmeService.findByTitulo(filme.getTitulo()) != null) {
             
            return new ResponseEntity(new CustomErrorType("Um filme com titulo [  " + 
            		filme.getTitulo()+ " ] já foi cadastrado !"),HttpStatus.CONFLICT);
        }
        
        filme = filmeService.addFilme(filme);
 
        return new ResponseEntity<Filme>(filme, HttpStatus.CREATED);
    }
    
	
	// -------------------Recuperar um  Filme ------------------------------------------

	@GetMapping(value = "/apiRest/filme/get/{id}")
	public ResponseEntity<Filme> getFilmeId(@PathVariable("id") Long id) {
 
		Filme filme = filmeService.findbyCodigo(id);
		
		if (filme==null) {
		 
			return new ResponseEntity(new CustomErrorType("O Filme  com " + id + "  não foi  encontrado !"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Filme>(filme, HttpStatus.OK);
	}
	
	 

	 // ------------------- Update Filme ------------------------------------------------
	 
    @PostMapping(value = "/apiRest/filme/update/{id}")
    public ResponseEntity<Filme> updateFilme(@PathVariable("id") long id, @RequestBody Filme filme) {
       
       // recupera o filme pelo Id
    	Filme filmeCurrent = filmeService.getFilme(id);
 
        if (filmeCurrent == null) { 
 
        	return new ResponseEntity(new CustomErrorType("O Filme com id " + id + "  não foi  encontrado !"), HttpStatus.NOT_FOUND);
        }

        // atualiza os dados do filme
        filmeCurrent.setTitulo(filme.getTitulo());
        filmeCurrent.setSinopse(filme.getSinopse());
 
       filmeService.updateFilme(filmeCurrent);
        return new ResponseEntity<Filme>(filmeCurrent, HttpStatus.OK);
    }
    
    
 // -------------------Remover  um  Filme ------------------------------------------

 	@GetMapping(value = "/apiRest/filme/remove/{id}")
 	public ResponseEntity<Filme> removeFilme(@PathVariable("id") Long id) {
  
 		Filme filme = filmeService.findbyCodigo(id);
 		
 		if (filme==null) {
 		 
 			return new ResponseEntity(new CustomErrorType("O Filme  com " + id + "  não foi  encontrado !"), HttpStatus.NOT_FOUND);
 		}
 		
 		filmeService.delete(filme);
 		
 		return new ResponseEntity(new CustomErrorType("O Filme  removido com suceso !"), HttpStatus.OK);
 	}
	
}
