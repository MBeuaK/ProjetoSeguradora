package com.projeto.seguradora.model;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "T_APOLICE")
@EqualsAndHashCode(callSuper = false)
public class Apolice {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMERO_APOLICE", unique = true)
    private Long numeroApolice;

    @Column(name = "DT_INICIO_VIGENCIA")
    private Date dtInicioVigencia;

    @Column(name = "DT_FIM_VIGENCIA")
    private Date dtFimVigencia;

    @Column(name = "PLACA_VEICULO")
    private String placaVeiculo;

    @Column(name = "VALOR")
    private Double valor;

    @OneToOne
    @JoinColumn(name = "FK_CLIENTE")
    private Cliente cliente;

}

