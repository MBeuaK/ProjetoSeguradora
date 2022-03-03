package com.projeto.seguradora.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private String nomeCompleto;
    private String cpf;
    private String uf;
    private String cidade;

}

