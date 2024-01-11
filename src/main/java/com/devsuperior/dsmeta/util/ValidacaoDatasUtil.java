package com.devsuperior.dsmeta.util;

import com.devsuperior.dsmeta.dto.DatasDTO;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ValidacaoDatasUtil {
    public static DatasDTO validar (String minDate, String maxDate) {

        LocalDate dataFinal;
        LocalDate dataInicial;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (maxDate.equals("")) {
            dataFinal = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        } else {
            dataFinal = LocalDate.parse(maxDate, dateTimeFormatter);
        }

        if (minDate.equals("")) {
            dataInicial = dataFinal.minusYears(1L);
        } else {
            dataInicial = LocalDate.parse(minDate, dateTimeFormatter);
        }

        return new DatasDTO(dataInicial, dataFinal);
    }
}
