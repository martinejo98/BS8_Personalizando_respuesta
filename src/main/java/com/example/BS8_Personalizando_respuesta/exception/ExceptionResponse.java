package com.example.BS8_Personalizando_respuesta.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private Date timestamp;
    private int HttpCode;
    private String mensaje;

}
