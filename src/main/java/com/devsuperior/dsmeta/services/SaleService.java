package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.DatasDTO;
import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SellerSumDTO;
import com.devsuperior.dsmeta.util.ValidacaoDatasUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.util.StringUtils;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleDTO> searchReport (String minDate, String maxDate, String name, Pageable pageable) {
		DatasDTO datasDTO = ValidacaoDatasUtil.validar(minDate, maxDate);
		LocalDate dataInicial = datasDTO.getDataInicial();
		LocalDate dataFinal = datasDTO.getDataFinal();

		Page<Sale> result = repository.searchReport(dataInicial, dataFinal, name, pageable);
		return result.map(x -> new SaleDTO(x));
	}

	public List<SellerSumDTO> searchSummary  (String minDate, String maxDate) {
		DatasDTO datasDTO = ValidacaoDatasUtil.validar(minDate, maxDate);
		LocalDate dataInicial = datasDTO.getDataInicial();
		LocalDate dataFinal = datasDTO.getDataFinal();

		List<SellerSumDTO>  sellerSumDTOS = repository.searchSummary(dataInicial, dataFinal);
		return sellerSumDTOS;
	}
}
