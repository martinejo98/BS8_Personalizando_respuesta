package com.example.BS8_Personalizando_respuesta.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //CREO UN ID AUTOGENERADO
    @NotNull
    int id_persona;

    @Column
    @NotBlank(message = "No puede estar vacio")                         //Esta etiqueta de hibernate hace que el valor tenga un mínimo de 6 caracteres y un máximo de 10
    @Size(min = 6, max = 10)                                            //Esta etiqueta de hibernate comprueba que ni este vacío, ni nulo
    private String usuario;

    @Column
    @NotBlank(message = "No puede estar vacio")
    private String password;

    @Column
    @NotBlank(message = "No puede estar vacio")
    private String name;

    @Column
    @NotBlank(message = "No puede estar vacio")
    private String surname;

    @Column
    @NotBlank(message = "No puede estar vacio")
    @Email                                                              //Esta etiqueta de hibernate comprueba que sea un email
    private String company_email;

    @Column
    @NotBlank(message = "No puede estar vacio")
    @Email
    private String personal_email;

    @Column
    @NotBlank(message = "No puede estar vacio")
    private String city;

    @Column
    @NotNull
    private Boolean active;

    @Column
    @NotBlank(message = "No puede estar vacio")
    private String created_date;

    @Column
    @NotBlank(message = "No puede estar vacio")
    private String imagen_url;

    @Column
    @NotBlank(message = "No puede estar vacio")
    private String termination_date;

    /**
     * El orden de más restrictivo a menos es:
     * NotBlank, NotNull o NotEmpty*/

}
