package com.devsuperior.dsmeta.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DatasDTO {

    private LocalDate dataInicial;

    private LocalDate dataFinal;
}
