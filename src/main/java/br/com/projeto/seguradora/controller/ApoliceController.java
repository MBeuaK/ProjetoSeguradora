package br.com.projeto.seguradora.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.seguradora.model.dto.ApoliceDTO;
import com.projeto.seguradora.model.dto.BaseResponseDTO;

import br.com.projeto.seguradora.service.ApoliceService;
import br.com.projeto.seguradora.util.MensagemUtil;

@RestController
@RequestMapping("/api/v1/apolice")
public class ApoliceController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(ApoliceController.class);

	@Autowired
	private ApoliceService apoliceService;


	@PostMapping
	public ResponseEntity<BaseResponseDTO> criar(@RequestBody ApoliceDTO apoliceDTO) {
		try {
			return create(apoliceService.criarApolice(apoliceDTO));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return error(MensagemUtil.ERRO_DESCONHECIDO);
		}
	}

	@GetMapping(value = "/buscar")
	public ResponseEntity<BaseResponseDTO> buscar() {
		try {
			return ok(apoliceService.buscarTodasApolices());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return error(MensagemUtil.ERRO_DESCONHECIDO);
		}
	}

	@GetMapping(value = "/buscar-apolice/{id}")
	public ResponseEntity<BaseResponseDTO> buscarPorId(@PathVariable(value = "id", required = true) Long id) {
		try {
			return ok(apoliceService.buscarApolicePorId(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return error(MensagemUtil.ERRO_DESCONHECIDO);
		}
	}

	@PutMapping("/editar-apolice/{id}")
	public ResponseEntity<BaseResponseDTO> editar(@PathVariable(value = "id", required = true) Long id,
			@RequestBody(required = true) ApoliceDTO apoliceDTO) {
		try {
			return ok(apoliceService.editarApolice(id, apoliceDTO));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return error(MensagemUtil.ERRO_DESCONHECIDO);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BaseResponseDTO> cancelar(@PathVariable(value = "id", required = true) Long id) {
		try {
			Object response = apoliceService.cancelarApolice(id);
			if (response == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new BaseResponseDTO(404, "Nenhuma apolice encontrada com o id informado.", null));
			}
			return ok(response);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return error(MensagemUtil.ERRO_DESCONHECIDO);
		}
	}

}
