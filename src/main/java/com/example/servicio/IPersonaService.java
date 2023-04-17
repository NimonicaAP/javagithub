package com.example.servicio;

import com.example.domain.Persona;
import java.util.List;

public interface IPersonaService {
    
    public List<Persona> listarPersonas();
    public void guardar(Persona persona);
    public void eliminar(Persona persona);
    public Persona encontrar(Persona persona);
}
