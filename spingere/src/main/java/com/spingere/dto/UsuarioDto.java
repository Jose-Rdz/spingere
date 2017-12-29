package com.spingere.dto;

import java.io.Serializable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author G13380
 */
public class UsuarioDto implements Serializable {

    private static final long serialVersionUID = -6823629936403129846L;

    private Integer idUsuario; 
    
    @NotBlank
    private String usuario;
    
    @NotBlank
    private String nombre;
    
    @NotBlank
    private String apPaterno;
    
    @NotBlank
    private String apMaterno;        
    
    @Email
    private String email;
    
    @NotBlank
    private String contrasena;
        
    private Integer tipoCliente;

    public UsuarioDto() {
    }

    public UsuarioDto(String usuario, String nombre, String apPaterno, String apMaterno, String email, String contrasena, Integer tipoCliente) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.email = email;
        this.contrasena = contrasena;
        this.tipoCliente = tipoCliente;
    }

    public UsuarioDto(Integer idUsuario, String usuario, String nombre, String apPaterno, String apMaterno, String email, String contrasena, Integer tipoCliente) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.email = email;
        this.contrasena = contrasena;
        this.tipoCliente = tipoCliente;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }        

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String toString() {
        return "UserDto{" + "idUsuario=" + idUsuario + ", usuario=" + usuario + ", nombre=" + nombre + ", apPaterno=" + apPaterno + ", apMaterno=" + apMaterno + ", email=" + email + ", contrasena=" + contrasena + ", tipoCliente=" + tipoCliente + '}';
    }   
    
}
