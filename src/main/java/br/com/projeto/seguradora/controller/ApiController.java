package br.com.projeto.seguradora.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {

    @ApiOperation(value = "Verifica se a aplicação está de pé", notes = "Verifica se a aplicação está de pé")
    @GetMapping
    public String healthCheck() {
        return "Alive!";
    }

}