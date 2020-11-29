package com.aularuix.back.security.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aularuix.back.dto.Mensaje;
import com.aularuix.back.security.dto.JwtDto;
import com.aularuix.back.security.dto.LoginUsuario;
import com.aularuix.back.security.dto.NuevoUsuario;
import com.aularuix.back.security.entity.Rol;
import com.aularuix.back.security.entity.Usuario;
import com.aularuix.back.security.enums.RolNombre;
import com.aularuix.back.security.jwt.JwtProvider;
import com.aularuix.back.security.service.RolService;
import com.aularuix.back.security.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	public static final int ONE = 1;
	
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        //Usuario y contraseña generada automaticamente
        String nombreUsuario = nuevoUsuario.getNombre().substring(0,3).concat(nuevoUsuario.getApellido1().substring(0,3)).concat(nuevoUsuario.getDni().substring(0,3));
        String password = nuevoUsuario.getNombre().substring(0,3).concat(nuevoUsuario.getApellido1().substring(0,3)).concat(nuevoUsuario.getDni().substring(0,3));
        
        		
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getApellido1(), nuevoUsuario.getApellido2(),
        		nuevoUsuario.getDni(), nuevoUsuario.getCalle(), nuevoUsuario.getNumCalle(), nuevoUsuario.getTelefono1(), 
        		nuevoUsuario.getFechaNac(), nuevoUsuario.getLocalidad(), nuevoUsuario.getProvincia(), nuevoUsuario.getCodigoPostal(), nombreUsuario, nuevoUsuario.getEmail(), passwordEncoder.encode(password), null, nuevoUsuario.getRolPrincipal(), null, false);
        
        List<Rol> roles = new ArrayList();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        
        if (usuario.getRolPrincipal().contains("Admin")) {
        	roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		}
        if (usuario.getRolPrincipal().contains("Profesor")) {
        	roles.add(rolService.getByRolNombre(RolNombre.ROLE_PROF).get());
		}

        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){     	
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
