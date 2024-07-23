package com.api.jugador.service;

import com.api.jugador.domain.Jugador;

import java.util.List;
import java.util.Optional;

public interface JugadorService {
    List<Jugador> getAllJugadores();
    Optional<Jugador> getJugadorById(Long id);
    Jugador saveJugador(Jugador jugador);
    void deleteJugadorById(Long id);


}
