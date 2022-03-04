package br.com.projeto.seguradora.service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.seguradora.model.Apolice;
import com.projeto.seguradora.model.dto.BackOfficeApoliceDTO;

import br.com.projeto.seguradora.controller.BaseController;
import br.com.projeto.seguradora.repository.ApoliceRepository;
import br.com.projeto.seguradora.util.MensagemUtil;

@Service
public class BackOfficeService extends BaseController {

	final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	ApoliceRepository apoliceRepository;

	public Object buscarApoliceValida(String numeroApolice) {
		Apolice apolice = apoliceRepository.findByNumeroApolice(Long.parseLong(numeroApolice));

		BackOfficeApoliceDTO backOfficeApoliceDTO = new BackOfficeApoliceDTO();
		if (apolice != null) {
			backOfficeApoliceDTO.setNumeroApolice(apolice.getNumeroApolice());
			backOfficeApoliceDTO.setPlacaVeiculo(apolice.getPlacaVeiculo());
			backOfficeApoliceDTO.setValor(apolice.getValor());
		}

		LocalDate dataAtual = LocalDate.now();
		LocalDate dataFim = null;

		if (apolice.getDtFimVigencia() != null) {
			dataFim = apolice.getDtFimVigencia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}

		if (dataAtual.isBefore(dataFim)) { //Apolice não venceu
			long diferencaEmDias = ChronoUnit.DAYS.between(dataAtual, dataFim);
			backOfficeApoliceDTO.setMsgVencimento(MessageFormat.format(MensagemUtil.APOLICE_VALIDA, diferencaEmDias));
		}

		if (dataAtual.isAfter(dataFim)) { //Apolice venceu
			long diferencaEmDias = ChronoUnit.DAYS.between(dataFim, dataAtual);
			backOfficeApoliceDTO.setMsgVencimento(MessageFormat.format(MensagemUtil.APOLICE_VENCIDA, diferencaEmDias));
		}

		return backOfficeApoliceDTO;
	}

}
