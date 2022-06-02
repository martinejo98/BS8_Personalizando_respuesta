package com.example.BS8_Personalizando_respuesta.infrastructure;

import com.example.BS8_Personalizando_respuesta.application.service.PersonaService;
import com.example.BS8_Personalizando_respuesta.exception.NotFoundException;
import com.example.BS8_Personalizando_respuesta.exception.UnprocesableException;
import com.example.BS8_Personalizando_respuesta.infrastructure.dto.input.PersonaInputDTO;
import com.example.BS8_Personalizando_respuesta.infrastructure.dto.output.PersonaOutputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/addPersona")
    public String addPersona(@RequestBody @Valid PersonaInputDTO personaInputDTO) {            //@valid valida que lso datos que le paso sean correctos.
        personaService.addPersona(personaInputDTO);
        return "Persona añadida";
    }

    @GetMapping("/getPersonaID/{id}")
    public PersonaOutputDTO getPersonaByID(@PathVariable int id) {
        return personaService.getPersonaByID(id);
    }


    @GetMapping("/getPersonaName/{name}")
    public List<PersonaOutputDTO> getPersonaByName(@PathVariable String name){
        return personaService.getPersonaByName(name);
    }

    @GetMapping("/getAll")
    public List <PersonaOutputDTO> getAll(){
        return personaService.getAll();
    }

    @PutMapping("/update/{id}")
    public String updatePersona(@RequestBody @Valid PersonaInputDTO persona, @PathVariable int id) throws UnprocesableException{
        checkPersona(persona);
        personaService.updatePersona(id, persona);
        return "Persona actualziada";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePersona(@PathVariable int id) {
        personaService.deletePersona(id);
        return "Persona eliminada";
    }

  private void checkPersona(PersonaInputDTO personaInputDTO) {
    if (personaInputDTO == null) {
      throw new UnprocesableException("No se puede introducir una persona sin información");
    }
    if (personaInputDTO.getUsuario() == null) {
      throw new UnprocesableException("El campo Usuario no puede ser nulo");
    }
    if (personaInputDTO.getUsuario() == null || personaInputDTO.getUsuario().isBlank()) {
      throw new UnprocesableException("El campo Usuario no puede ser nulo ni estar vacío");
    }
    if (personaInputDTO.getUsuario().length() > 10) {
      throw new UnprocesableException(
          "El campo Usuario debe tener como máximo 10 caracteres de longitud");
    }
    if (personaInputDTO.getUsuario().length() < 6) {
      throw new UnprocesableException(
          "El campo Usuario debe tener como mínimo 6 caracteres de longitud");
    }
    if (personaInputDTO.getPassword() == null) {
      throw new UnprocesableException("El campo Password no puede ser nulo");
    }
    if (personaInputDTO.getPassword() == null || personaInputDTO.getPassword().isBlank()) {
      throw new UnprocesableException("El campo Password no puede ser nulo ni estar vacío");
    }
    if (personaInputDTO.getName() == null) {
      throw new UnprocesableException("El campo nombre no puede ser nulo");
    }
    if (personaInputDTO.getName() == null || personaInputDTO.getName().isBlank()) {
      throw new UnprocesableException("El campo nombre no puede ser nulo ni estar vacío");
    }
    if (personaInputDTO.getCompany_email() == null) {
      throw new UnprocesableException("El campo Company_Email no puede ser nulo");
    }
    if (personaInputDTO.getCompany_email() == null
        || personaInputDTO.getCompany_email().isBlank()) {
      throw new UnprocesableException("El campo Company_Email no puede ser nulo ni estar vacío");
    }
    if (personaInputDTO.getPersonal_email() == null) {
      throw new UnprocesableException("El campo Personal_Email no puede ser nulo");
    }
    if (personaInputDTO.getPersonal_email() == null
        || personaInputDTO.getPersonal_email().isBlank()) {
      throw new UnprocesableException("El campo Personal_Email no puede ser nulo ni estar vacío");
    }
    if (personaInputDTO.getCity() == null) {
      throw new UnprocesableException("El campo Ciudad no puede ser nulo");
    }
    if (personaInputDTO.getCity() == null || personaInputDTO.getCity().isBlank()) {
      throw new UnprocesableException("El campo Ciudad no puede ser nulo ni estar vacío");
    }
    if (personaInputDTO.getCreated_date() == null) {
      throw new UnprocesableException("El campo Created-Date no puede ser nulo");
    }
  }
}
