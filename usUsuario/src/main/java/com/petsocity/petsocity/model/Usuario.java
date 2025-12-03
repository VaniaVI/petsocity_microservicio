package com.petsocity.petsocity.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({"id", "nombre", "apellido", "email", "contrasenia", "telefono", "direccion", "region" , "comuna", "fecha_creacion"})  
@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo obligatorio")
    @Column(name = "nombre" ,nullable = false)
    private String nombre;

    @NotBlank(message = "Campo obligatorio")
    @Column(name = "apellido", nullable = false)
    private String apellido;
    
    @Email(message = "El correo no tiene un formato valido")
    @Pattern(
        regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
        message = "Debe tener el formato nombre@duoc.cl")
    @NotBlank(message = "Campo obligatorio")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Campo obligatorio")
    @Size(min = 6, max = 255, message = "La contraseña debe tener al menos 6 caracteres")
    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @Column(name = "telefono", nullable = false, columnDefinition = "TEXT")
    private String telefono;

    @NotBlank(message = "Campo obligatorio")
    @Column(name = "direccion", nullable = false, columnDefinition = "TEXT")
    private String direccion;

    @NotBlank(message = "Campo obligatorio")
    @Column(name = "region", nullable = false, columnDefinition = "TEXT")
    private String region;

    @NotBlank(message = "Campo obligatorio")
    @Column(name = "comuna", nullable = false, columnDefinition = "TEXT")
    private String comuna;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
}
