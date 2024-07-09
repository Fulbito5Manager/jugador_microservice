package com.api.jugador.controller;

import com.api.jugador.domain.Jugador;
import com.api.jugador.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {
    @Autowired
    private JugadorService jugadorService;

    @GetMapping
    public List<Jugador> getAllJugadores(){
        return jugadorService.getAllJugadores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> getJugadorById(@PathVariable Long id){
        Optional<Jugador> Jugador = jugadorService.getJugadorById(id);
        return Jugador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Jugador createJugador(@RequestBody Jugador jugador){
        return jugadorService.saveJugador(jugador);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Jugador> updateJugador(@PathVariable Long id, @RequestBody Jugador jugadorDetails){
        Optional<Jugador> jugador = jugadorService.getJugadorById(id);
        if (jugador.isPresent()){
            Jugador existingJugador = jugador.get();
            existingJugador.setNombre(jugadorDetails.getNombre());
            existingJugador.setMedia(jugadorDetails.getMedia());
            existingJugador.setEmail(jugadorDetails.getEmail());
            Jugador updateJugador = jugadorService.saveJugador(existingJugador);
            return  ResponseEntity.ok(updateJugador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
