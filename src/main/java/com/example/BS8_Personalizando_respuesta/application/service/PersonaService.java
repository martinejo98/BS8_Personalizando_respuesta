package com.example.BS8_Personalizando_respuesta.application.service;

import com.example.BS8_Personalizando_respuesta.domain.Persona;
import com.example.BS8_Personalizando_respuesta.exception.NotFoundException;
import com.example.BS8_Personalizando_respuesta.exception.UnprocesableException;
import com.example.BS8_Personalizando_respuesta.infrastructure.dto.input.PersonaInputDTO;
import com.example.BS8_Personalizando_respuesta.infrastructure.dto.output.PersonaOutputDTO;
import com.example.BS8_Personalizando_respuesta.repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ModelMapper modelMapper;

    public PersonaOutputDTO addPersona(PersonaInputDTO persona) throws UnprocesableException {
        if(persona.getUsuario() == null || persona.getPassword() == null || persona.getName() == null || persona.getCompany_email() == null
                || persona.getPersonal_email() == null || persona.getCity() == null || persona.getActive() == null || persona.getCreated_date() == null
                || persona.getUsuario().length() > 10) throw new UnprocesableException("Los campos no se han establecido de manera correcta");
            personaRepository.save(modelMapper.map(persona, Persona.class));
        return modelMapper.map(persona, PersonaOutputDTO.class);
    }

    public PersonaOutputDTO getPersonaByID(int id) {
        Persona persona = personaRepository.findById(id).orElseThrow(()-> new NotFoundException("No se ha encontrado a nadie con el id: "+id));
        PersonaOutputDTO peDTO = modelMapper.map(persona, PersonaOutputDTO.class);
        return peDTO;
    }

    public List<PersonaOutputDTO> getPersonaByName(String name){
        List <PersonaOutputDTO> listaPersonas = new ArrayList<>();
        personaRepository.findAll().forEach(
                person -> {
                    if(person.getName().equals(name)){
                        PersonaOutputDTO peDTO = modelMapper.map(person, PersonaOutputDTO.class);
                        listaPersonas.add(peDTO);
                    }
                }
        );

        return listaPersonas;
    }

    public List<PersonaOutputDTO> getAll(){
        List <PersonaOutputDTO> listaPersonas = new ArrayList<>();
        personaRepository.findAll().forEach(
                person -> {
                    PersonaOutputDTO peDTO = modelMapper.map(person, PersonaOutputDTO.class);
                    listaPersonas.add(peDTO);
                }
        );
        return listaPersonas;
    }

    /**
     * Este metodo lo que hace esc convertirme todas las persona que encuentra el findAll() en personasDTO
     * y me lo añade a la lista de personasDTO que he creado y la retorna
     */

    public PersonaOutputDTO updatePersona(int id, PersonaInputDTO persona) {
        Optional<Persona> personEntity = personaRepository.findById(id);
        if(personEntity.isPresent()){

            persona.setId_persona(id);
            persona.setUsuario(Optional.ofNullable(persona.getUsuario()).orElse(personEntity.get().getUsuario()));
            persona.setPassword(Optional.ofNullable(persona.getPassword()).orElse(personEntity.get().getPassword()));
            persona.setName(Optional.ofNullable(persona.getName()).orElse(personEntity.get().getName()));
            persona.setSurname(Optional.ofNullable(persona.getSurname()).orElse(personEntity.get().getSurname()));
            persona.setCompany_email(Optional.ofNullable(persona.getCompany_email()).orElse(personEntity.get().getCompany_email()));
            persona.setPersonal_email(Optional.ofNullable(persona.getPersonal_email()).orElse(personEntity.get().getPersonal_email()));
            persona.setCity(Optional.ofNullable(persona.getCity()).orElse(personEntity.get().getCity()));
            persona.setImagen_url(Optional.ofNullable(persona.getImagen_url()).orElse(personEntity.get().getImagen_url()));
            persona.setActive(personEntity.get().getActive());
            persona.setCreated_date(personEntity.get().getCreated_date());

            personaRepository.saveAndFlush(modelMapper.map(persona, Persona.class));
            PersonaOutputDTO personDTO = modelMapper.map(persona, PersonaOutputDTO.class);
            return personDTO;
        } else {
                throw new NotFoundException("No se ha encontrado a nadie con el id: "+id);
        }
    }

    public String deletePersona(int id) throws NotFoundException{
        Optional <Persona> persona = personaRepository.findById(id);
        if(persona.isPresent()){
             personaRepository.deleteById(id);
             return "Persona eliminada";
        }else{
            throw new NotFoundException("No se ha encontrado a nadie con el id: "+id);
        }
    }
}
