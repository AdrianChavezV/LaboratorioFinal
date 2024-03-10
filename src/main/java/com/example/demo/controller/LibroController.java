package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Genero;
import com.example.demo.model.Libro;
import com.example.demo.service.GeneroService;
import com.example.demo.service.LibroService;

@Controller
@RequestMapping("/libro")
public class LibroController {
	
	
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/libros")
	public String getAllLibros(Model model) {
		
		List<Libro> listLibro = libroService.getAllLibros();
		
		model.addAttribute("libros", listLibro);
		
		
		return "listLibros";
	}
	
	@GetMapping("/register")
	public String Register(Model model) { 
		List<Genero> generos = generoService.getAllGeneros();
	    model.addAttribute("generos", generos);
		
		return "libroRegister";
	}
	
	@PostMapping("/register")
	public String createLibro1(@RequestParam("nombreLibro") String nombreLibro, @RequestParam("autor") String autor,@RequestParam("fechaPublicacion") String fechaPublicacion,@RequestParam("id") Long id, Model model) {
		
		
		
		Libro libro = new Libro();
		libro.nombreLibro = nombreLibro;
		libro.autor=autor;
		libro.fechaPublicacion = fechaPublicacion;
		
		Genero genero = generoService.getGenerosById(id);
		
		libro.genero=genero;
		
		
		libroService.createLibro(libro);
		
		model.addAttribute("libros", libroService.getAllLibros());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "listLibros";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long idLibro, Model model) {
		
		Libro libro = libroService.getLibrosById(idLibro);
		
		model.addAttribute("libros", libro);
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "libroEdit";
	}
	
	@PostMapping("/edit")
	public String createLibro(@RequestParam("nombreLibro") String nombreLibro, @RequestParam("autor") String autor,@RequestParam("fechaPublicacion") String fechaPublicacion,@RequestParam("id") Long id, Model model) {
		
		
		
		Libro libro = libroService.getLibrosById(id);
		libro.nombreLibro = nombreLibro;
		libro.autor=autor;
		libro.fechaPublicacion = fechaPublicacion;
		
		Genero genero = generoService.getGenerosById(id);
		
		libro.genero=genero;
		
		
		libroService.createLibro(libro);
		
		model.addAttribute("libros", libroService.getAllLibros());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "listLibros";
	}
	
	@GetMapping("/delete/{idLibro}")
	public String deleteLibro(@PathVariable Long id, Model model) {
	    libroService.deleteLibro(id);

	    model.addAttribute("libros", libroService.getAllLibros());
	    model.addAttribute("generos", generoService.getAllGeneros());

	    return "listLibros";
	}

	

}
