package com.example.BS8_Personalizando_respuesta.infrastructure;

import com.example.BS8_Personalizando_respuesta.application.service.PersonaService;
import com.example.BS8_Personalizando_respuesta.exception.NotFoundException;
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
    public String addPersona(@RequestBody @Valid PersonaInputDTO personaInputDTO) throws Exception {            //@valid valida que lso datos que le paso sean correctos.
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
    public String addPersona(@RequestBody @Valid PersonaInputDTO persona, @PathVariable int id) {
        personaService.updatePersona(id, persona);
        return "Persona actualziada";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePersona(@PathVariable int id) {
        personaService.deletePersona(id);
        return "Persona eliminada";
    }
}
