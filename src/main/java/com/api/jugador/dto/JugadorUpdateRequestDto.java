package com.api.jugador.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JugadorUpdateRequestDto {
    private String nombre;
    private Long media;
    private String email;
}
