package com.hfuentealba.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorPeliculas {
    private static HashMap<String, String> listaPeliculas = new HashMap<>();
    public ControladorPeliculas() {
        listaPeliculas.put("Winnie the Pooh", "Don Hall");
        listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
        listaPeliculas.put("Tarzán", "Kevin Lima");
        listaPeliculas.put("Mulán", "Barry Cook");
        listaPeliculas.put("Oliver", "Kevin Lima");
        listaPeliculas.put("Big Hero 6", "Don Hall");
    }
    
	@GetMapping ("/peliculas")
	public String obtenerTodasLasPeliculas() { 
		StringBuilder resultado = new StringBuilder("Lista de películas:\n");
        listaPeliculas.forEach((pelicula, director) -> 
            resultado.append(pelicula).append(" - Director: ").append(director).append("\n")
        );
        return resultado.toString();
	}
	
    @GetMapping("/peliculas/{nombre}")
    public String obtenerPeliculaPorNombre(@PathVariable String nombre) {
        if (listaPeliculas.containsKey(nombre)) {
            return "Película: " + nombre + " - Director: " + listaPeliculas.get(nombre);
        } else {
            return "La película no se encuentra en nuestra lista.";
        }
    }
    
    @GetMapping("/peliculas/director/{nombre}")
    public String obtenerPeliculasPorDirector(@PathVariable String nombre) {
        List<String> peliculasPorDirector = new ArrayList<>();
        for (Entry<String, String> entrada : listaPeliculas.entrySet()) {
            if (entrada.getValue().equalsIgnoreCase(nombre)) {
                peliculasPorDirector.add(entrada.getKey());
            }
        }
        if (peliculasPorDirector.isEmpty()) {
            return "No contamos con películas con ese director en nuestra lista.";
        } else {
            return "Películas dirigidas por " + nombre + ": " + String.join(", ", peliculasPorDirector);
        }
    }
}
