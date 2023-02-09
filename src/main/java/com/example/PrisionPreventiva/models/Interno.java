package com.example.PrisionPreventiva.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "internos")
public class Interno {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;
    @Getter @Setter @Column(name = "circunscripcion")
    private String circunscripcion;
    @Getter @Setter @Column(name = "fiscalia")
    private String fiscalia;
    @Getter @Setter @Column(name = "nombre")
    private String nombreCompleto;
    @Getter @Setter @Column(name = "expediente")
    private String expediente;
    @Getter @Setter @Column(name = "fechaDetenido")
    private LocalDate fechaDetenido;
    @Getter @Setter @Column(name = "fechaPreventiva")
    private LocalDate fechaPreventiva;
    @Getter @Setter @Column(name = "fechaLimite")
    private LocalDate fechaLimite;
    @Getter @Setter @Column(name = "observaciones")
    private String observaciones;


    /*

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>*/


}
