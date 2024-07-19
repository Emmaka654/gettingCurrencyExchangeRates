package com.gettingCurrencyExchangeRates.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

/**
 * Data model for currency exchange rates.
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Currency {

    @Id
    @JsonProperty("Cur_ID")
    private Long id;
    @JsonProperty("Date")
    private LocalDate date;
    @JsonProperty("Cur_Abbreviation")
    private String abbreviation;
    @JsonProperty("Cur_Scale")
    private Long scale;
    @JsonProperty("Cur_Name")
    private String name;
    @JsonProperty("Cur_OfficialRate")
    private Double rate;
}
