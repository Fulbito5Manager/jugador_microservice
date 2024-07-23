package com.api.jugador.controller;

import com.api.jugador.domain.Jugador;
import com.api.jugador.dto.JugadorUpdateRequestDto;
import com.api.jugador.exception.JugadorIsNotExistException;
import com.api.jugador.service.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jugadores")
@Tag(name = "Jugador Management System", description = "Operations pertaining to team in Equipo Management System")
public class JugadorController {
    @Autowired
    private JugadorService jugadorService;
    @Operation(summary = "Devuelve una lista de Jugadores con todos los jugadores")
    @GetMapping
    public List<Jugador> getAllJugadores(){
        return jugadorService.getAllJugadores();
    }
    @Operation(summary = "Devuelve un jugador con un id de jugador particular")
    @GetMapping("/{id}")
    public ResponseEntity<Jugador> getJugadorById(
            @Parameter(description = "ID del jugador a devolver", required = true)
            @PathVariable Long id){
        Optional<Jugador> Jugador = jugadorService.getJugadorById(id);
        return Jugador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Crea un jugador")
    @PostMapping
    public Jugador createJugador(
            @Parameter(description = "Objeto jugador a crear", required = true)
            @RequestBody Jugador jugador){
        return jugadorService.saveJugador(jugador);
    }

    @Operation(summary = "Devuelve un Jugador con un atributo actualizado")
    @PatchMapping("/{id}")
    public ResponseEntity<Jugador> actualizarJugadorById(
            @Parameter(description = "ID del Jugador a parchear", required = true)
            @PathVariable Long id,
            @Parameter(description = "Objeto Request para parchear un Jugador", required = true)
            @RequestBody JugadorUpdateRequestDto jugadorUpdateRequestDto) {
        Optional<Jugador> jugador = jugadorService.getJugadorById(id);
        if (jugador.isPresent()) {
            Jugador JugadorEncontrado = jugador.get();
            if (jugadorUpdateRequestDto.getNombre() != null) {
                JugadorEncontrado.setNombre(jugadorUpdateRequestDto.getNombre());
            }
            if (jugadorUpdateRequestDto.getMedia() != null) {
                JugadorEncontrado.setMedia(jugadorUpdateRequestDto.getMedia());
            }
            if (jugadorUpdateRequestDto.getEmail() != null) {
                JugadorEncontrado.setEmail(jugadorUpdateRequestDto.getEmail());
            }
            Jugador updatedJugador = jugadorService.saveJugador(JugadorEncontrado);
            return ResponseEntity.ok(updatedJugador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "actualiza un jugador con un id de jugador particular")
    @PutMapping("/{id}")
    public  ResponseEntity<Jugador> updateJugador(
            @Parameter(description = "ID del jugador a actualizar", required = true)
            @PathVariable Long id,
            @Parameter(description = "Objeto jugador actualizado", required = true)
            @RequestBody Jugador jugadorDetails){
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
    @Operation(summary = "Borra un jugador con un id de jugador particular")
    @DeleteMapping("/{id}")
    public void deleteJugadorById(
            @Parameter(description = "ID del jugador a borrar", required = true)
            @PathVariable Long id){
        Optional<Jugador> jugador = jugadorService.getJugadorById(id);
        if(jugador.isPresent())
            jugadorService.deleteJugadorById(id);
        throw new JugadorIsNotExistException("El jugador que quieres borrar no existe");
    }
}
