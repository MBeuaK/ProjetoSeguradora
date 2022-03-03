package com.projeto.seguradora.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApoliceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long numeroApolice;
    private Date dtInicioVigencia;
    private Date dtFimVigencia;
    private String placaVeiculo;
    private Double valor;
    private Long idCliente;

}
