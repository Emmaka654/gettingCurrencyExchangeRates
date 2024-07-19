package com.gettingCurrencyExchangeRates.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Currency {

    @Id
    private Long id;
    private LocalDate date;
    private String abbreviation;
    private Long scale;
    private String name;
    private Double rate;
}
