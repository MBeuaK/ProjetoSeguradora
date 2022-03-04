package com.projeto.seguradora.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackOfficeApoliceDTO {

    private Long numeroApolice;
    private String placaVeiculo;
    private Double valor;
    private String msgVencimento;

}
