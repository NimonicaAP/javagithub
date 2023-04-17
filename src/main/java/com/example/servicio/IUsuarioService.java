package com.example.servicio;

import com.example.dao.IUsuarioDao;
import com.example.domain.Rol;
import com.example.domain.Usuario;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j //Manejo login
public class IUsuarioService implements UserDetailsService {
    //Inyectar una instancia ya que se apoya de esta clase
    @Autowired
    private IUsuarioDao usuarioDao;
    //METODOS DE LA CLASE SERVICIO DEBEN DE SER TRANSACCIONALES
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Creamos una variable para encontrar el nombre del usuario
        Usuario usuario = usuarioDao.findByUsername(username);
        
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        var roles = new ArrayList<GrantedAuthority>();//Envolver los roles porque se utilizo Jpa en GrantedAuthority
        for(Rol rol:usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        //Objeto de tipo user de generado de manera automática de Spring Security 
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
}
