package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SellerSumDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT obj " +
            "FROM Sale obj " +
            "JOIN FETCH obj.seller " +
            "WHERE obj.date BETWEEN :dataInicial and :dataFinal " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) ",
        countQuery = "SELECT COUNT(obj) " +
                "FROM Sale obj " +
                "WHERE obj.date BETWEEN :dataInicial and :dataFinal " +
                "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) " )
    Page<Sale> searchReport (LocalDate  dataInicial, LocalDate dataFinal,  String name, Pageable pageable);

    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SellerSumDTO(obj.seller.name, SUM(obj.amount)) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :dataInicial and :dataFinal " +
            "GROUP BY obj.seller.name " )
    List<SellerSumDTO> searchSummary (LocalDate  dataInicial, LocalDate dataFinal);

}
