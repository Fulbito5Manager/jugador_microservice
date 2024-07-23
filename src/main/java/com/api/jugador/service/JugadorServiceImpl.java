package com.api.jugador.service;

import com.api.jugador.domain.Jugador;
import com.api.jugador.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorServiceImpl implements JugadorService{
    @Autowired
    private JugadorRepository jugadorRepository;

    @Override
    public List<Jugador> getAllJugadores() {
        return jugadorRepository.findAll();
    }

    @Override
    public Optional<Jugador> getJugadorById(Long id) {
        return jugadorRepository.findById(id);
    }

    @Override
    public Jugador saveJugador(Jugador jugador) {

        return jugadorRepository.save(jugador);
    }

    @Override
    public void deleteJugadorById(Long id) {
        jugadorRepository.deleteById(id);
    }


}
