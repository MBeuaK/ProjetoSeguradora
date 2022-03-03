package br.com.projeto.seguradora.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.seguradora.model.Apolice;
import com.projeto.seguradora.model.Cliente;
import com.projeto.seguradora.model.dto.ApoliceDTO;

import br.com.projeto.seguradora.repository.ApoliceRepository;
import br.com.projeto.seguradora.repository.ClienteRepository;
import br.com.projeto.seguradora.util.MensagemUtil;

@Service
public class ApoliceService {

	@Autowired
	private ApoliceRepository apoliceRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public Object criarApolice(ApoliceDTO apoliceDTO) {
		Optional<Cliente> cliente = clienteRepository.findById(apoliceDTO.getIdCliente());
		if (!cliente.isPresent()) {
			throw new IllegalArgumentException(
					MessageFormat.format(MensagemUtil.CLIENTE_NAO_ECONTRADO, apoliceDTO.getIdCliente()));
		}

		apoliceDTO.setNumeroApolice(Long.parseLong(gerarNumeroApolice()));
		Apolice apolice = new Apolice();
		BeanUtils.copyProperties(apoliceDTO, apolice);
		apolice.setCliente(cliente.get());
		return apoliceRepository.save(apolice);
	}

	public Object buscarTodasApolices() {
		List<Apolice> lstApolice = apoliceRepository.findAll();
		if (lstApolice.isEmpty()) {
			return new ArrayList<>();
		}

		return lstApolice;
	}

	public Object buscarApolicePorId(Long id) {
		Optional<Apolice> apolice = apoliceRepository.findById(id);
		if (!apolice.isPresent()) {
			throw new IllegalArgumentException(MessageFormat.format(MensagemUtil.APOLICE_NAO_ECONTRADO, id));
		}

		return apolice.get();
	}

	public Object cancelarApolice(Long id) {
		Optional<Apolice> apolice = apoliceRepository.findById(id);
		if (apolice.isPresent()) {
			apoliceRepository.deleteById(id);
			return MessageFormat.format(MensagemUtil.APOLICE_DELETADA_SUCESSO, id);
		}

		return null;
	}

	public Object editarApolice(Long id, ApoliceDTO apoliceDTO) {
		Optional<Apolice> apolice = apoliceRepository.findById(id);
		if (!apolice.isPresent()) {
			throw new IllegalArgumentException(MessageFormat.format(MensagemUtil.APOLICE_NAO_ECONTRADO, id));
		}

		Optional<Cliente> cliente = clienteRepository.findById(apoliceDTO.getIdCliente());
		if (!cliente.isPresent()) {
			throw new IllegalArgumentException(
					MessageFormat.format(MensagemUtil.CLIENTE_NAO_ECONTRADO, apoliceDTO.getIdCliente()));
		}

		apolice.get().setDtInicioVigencia(apoliceDTO.getDtInicioVigencia());
		apolice.get().setDtFimVigencia(apoliceDTO.getDtFimVigencia());
		apolice.get().setPlacaVeiculo(apoliceDTO.getPlacaVeiculo());
		apolice.get().setValor(apoliceDTO.getValor());
		apolice.get().setCliente(cliente.get());

		return apoliceRepository.save(apolice.get());
	}

	private String gerarNumeroApolice() {
		String random = null;
		Apolice apolice = null;
		boolean temUUID = false;

		while (temUUID == false) {
			random = RandomStringUtils.randomNumeric(5);
			apolice = apoliceRepository.findByNumeroApolice(Long.parseLong(random));
			if (apolice == null) {
				temUUID = true;
			}
		}

		return random;
	}

}
