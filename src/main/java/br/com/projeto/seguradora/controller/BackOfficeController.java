package br.com.projeto.seguradora.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projeto.seguradora.model.dto.BaseResponseDTO;

import br.com.projeto.seguradora.service.BackOfficeService;
import br.com.projeto.seguradora.util.MensagemUtil;
import io.swagger.annotations.ApiOperation;

public class BackOfficeController extends BaseController{
	
	private final Logger log = LoggerFactory.getLogger(BackOfficeController.class);

	@Autowired
	private BackOfficeService backOfficeService;

	@ApiOperation(value = "Busca a apolice pelo numero", notes = "Busca a apolice pelo numero")
	@GetMapping(value = "/buscar-apolice-numero")
	public ResponseEntity<BaseResponseDTO> buscarApoliceValida(@RequestParam(value = "numeroApolice", required = false) String numeroApolice) {
		try {
			return ok(backOfficeService.buscarApoliceValida(numeroApolice));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return error(MensagemUtil.ERRO_DESCONHECIDO);
		}
	}

}
