package br.com.projeto.seguradora.controller;

import java.text.MessageFormat;

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

import com.projeto.seguradora.model.dto.BaseResponseDTO;
import com.projeto.seguradora.model.dto.ClienteDTO;

import br.com.projeto.seguradora.service.ClienteService;
import br.com.projeto.seguradora.util.MensagemUtil;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(ApoliceController.class);

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<BaseResponseDTO> criar(@RequestBody ClienteDTO clienteDTO) {
		try {
			return create(clienteService.cadastrarCliente(clienteDTO));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return error(MensagemUtil.ERRO_DESCONHECIDO);
		}
	}

	@GetMapping(value = "/buscar")
	public ResponseEntity<BaseResponseDTO> buscar() {
		try {
			return ok(clienteService.buscarTodosClientes());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return error(MensagemUtil.ERRO_DESCONHECIDO);
		}
	}

	@GetMapping(value = "/buscar-cliente/{id}")
	public ResponseEntity<BaseResponseDTO> buscarPorId(@PathVariable(value = "id", required = true) Long id) {
		try {
			return ok(clienteService.buscarClientePorId(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return error(MensagemUtil.ERRO_DESCONHECIDO);
		}
	}

	@PutMapping("/editar-cliente/{id}")
	public ResponseEntity<BaseResponseDTO> editar(@PathVariable(value = "id", required = true) Long id,
			@RequestBody(required = true) ClienteDTO clienteDTO) {
		try {
			return ok(clienteService.editarCliente(id, clienteDTO));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return error(MensagemUtil.ERRO_DESCONHECIDO);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BaseResponseDTO> cancelar(@PathVariable(value = "id", required = true) Long id) {
		try {
			Object response = clienteService.excluirCliente(id);
			if (response == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new BaseResponseDTO(404, MessageFormat.format(MensagemUtil.CLIENTE_NAO_ECONTRADO, id), null));
			}
			return ok(response);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return error(MensagemUtil.ERRO_DESCONHECIDO);
		}
	}

}
