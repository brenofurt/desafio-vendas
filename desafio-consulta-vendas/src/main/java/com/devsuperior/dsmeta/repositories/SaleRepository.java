package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleReportProjectionDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryProjectionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleReportProjectionDTO(s.id, s.date, s.amount, s.seller.name) "
            + "FROM Sale s "
            + "WHERE s.date >= :minDate "
            + "AND s.date <= :maxDate "
            + "AND LOWER(s.seller.name) LIKE LOWER(CONCAT('%', :name, '%')) "
            + "ORDER BY s.amount DESC")
    Page<SaleReportProjectionDTO> getReport(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate, @Param("name") String name, Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryProjectionDTO(s.seller.name, SUM(s.amount)) "
            + "FROM Sale s "
            + "WHERE s.date >= :minDate "
            + "AND s.date <= :maxDate "
            + "GROUP BY s.seller.name "
            + "ORDER BY s.seller.name")

    Page<SaleSummaryProjectionDTO> getSummary(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate, Pageable pageable);
}

