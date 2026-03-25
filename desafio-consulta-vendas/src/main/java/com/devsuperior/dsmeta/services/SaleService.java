package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleSummaryProjectionDTO;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportProjectionDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportProjectionDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate minDateParsed;
		LocalDate maxDateParsed;

		if (maxDate == null || maxDate.isEmpty()){
			maxDateParsed = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else {
			maxDateParsed = LocalDate.parse(maxDate);
		}

		if (minDate == null || minDate.isEmpty()){
			minDateParsed = maxDateParsed.minusYears(1L);
		}
		else {
			minDateParsed = LocalDate.parse(minDate);
		}

		return repository.getReport(minDateParsed, maxDateParsed, name, pageable);
	}

	public Page<SaleSummaryProjectionDTO> getSummary(String minDate, String maxDate, Pageable pageable) {
		LocalDate minDateParsed;
		LocalDate maxDateParsed;

		if (maxDate == null || maxDate.isEmpty()){
			maxDateParsed = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else {
			maxDateParsed = LocalDate.parse(maxDate);
		}

		if (minDate == null || minDate.isEmpty()){
			minDateParsed = maxDateParsed.minusYears(1L);
		}
		else {
			minDateParsed = LocalDate.parse(minDate);
		}

		return repository.getSummary(minDateParsed, maxDateParsed, pageable);
	}

}
