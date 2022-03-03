package br.com.projeto.seguradora.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.seguradora.model.Cliente;
import com.projeto.seguradora.model.dto.ClienteDTO;

import br.com.projeto.seguradora.repository.ClienteRepository;
import br.com.projeto.seguradora.util.MensagemUtil;
import br.com.projeto.seguradora.util.ValidadorUtil;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Object cadastrarCliente(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		if (!ValidadorUtil.validarCPF(clienteDTO.getCpf())) {
			throw new IllegalArgumentException(MessageFormat.format(MensagemUtil.CPF_INVALIDO, clienteDTO.getCpf()));
		}

		Optional<Cliente> c = clienteRepository.findByCpf(clienteDTO.getCpf());
		if (c.isPresent()) {
			throw new IllegalArgumentException(
					MessageFormat.format(MensagemUtil.CPF_EXISTENTE, clienteDTO.getCpf()));
		}

		BeanUtils.copyProperties(clienteDTO, cliente);
		return clienteRepository.save(cliente);
	}

	public Object buscarTodosClientes() {
		List<Cliente> lstCliente = clienteRepository.findAll();
		if (lstCliente.isEmpty()) {
			return new ArrayList<>();
		}

		return lstCliente;
	}

	public Object buscarClientePorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent()) {
			throw new IllegalArgumentException(MessageFormat.format(MensagemUtil.CLIENTE_NAO_ECONTRADO, id));
		}

		return cliente.get();
	}

	public Object editarCliente(Long id, ClienteDTO ClienteDTO) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent()) {
			throw new IllegalArgumentException(MessageFormat.format(MensagemUtil.CLIENTE_NAO_ECONTRADO, id));
		}

		cliente.get().setCidade(ClienteDTO.getCidade());
		cliente.get().setNomeCompleto(ClienteDTO.getNomeCompleto());
		cliente.get().setUf(ClienteDTO.getUf());
		cliente.get().setCpf(ClienteDTO.getCpf());

		return clienteRepository.save(cliente.get());
	}

	public Object excluirCliente(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			clienteRepository.deleteById(id);
			return "ok";
		}

		return null;
	}

}
