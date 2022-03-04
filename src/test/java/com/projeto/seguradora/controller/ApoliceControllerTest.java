package com.projeto.seguradora.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.seguradora.model.dto.ApoliceDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class ApoliceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void obterTokenComSucesso() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"usuario\",\"password\":\"usuario@2022\"}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void obterTokenComFalha() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"usuario\",\"password\":\"errado\"}"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    public void criarApoliceComSucesso() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"usuario\",\"password\":\"usuario@2022\"}"))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getHeader("Authorization");

        this.mockMvc.perform(post("/api/v1/apolice")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockApolice())))
                .andExpect(status().isOk());
    }

    public void criarApoliceComFalha() throws Exception {
        this.mockMvc.perform(post("/api/v1/apolice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockApolice())))
                .andExpect(status().isUnauthorized());
    }

    public ApoliceDTO mockApolice() throws ParseException {
        ApoliceDTO request = new ApoliceDTO();

        String dtIncio = " 2022-01-01 00:00:00.0";
        String dtFIm = " 2022-03-01 00:00:00.0";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

        request.setDtInicioVigencia(sdf.parse(dtIncio));
        request.setDtFimVigencia(sdf.parse(dtFIm));
        request.setPlacaVeiculo("LQX2122");
        request.setValor(22.0);
        request.setIdCliente(1L);

        return request;
    }

}