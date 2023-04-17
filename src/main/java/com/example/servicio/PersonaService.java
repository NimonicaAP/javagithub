package com.example.servicio;

import com.example.dao.IPersonaDao;
import com.example.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaService implements IPersonaService{
    //Para que sea aotomatico
    @Autowired
    private IPersonaDao personaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        //De objetos a lista
        return (List<Persona>)personaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrar(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
