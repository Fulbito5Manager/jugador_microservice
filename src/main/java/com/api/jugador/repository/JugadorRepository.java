package com.api.jugador.repository;

import com.api.jugador.domain.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador,Long>{
}
