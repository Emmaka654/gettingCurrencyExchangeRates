package com.gettingCurrencyExchangeRates.repository;

import com.gettingCurrencyExchangeRates.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency getCurrencyByDateAndId(LocalDate date, Long currencyId);
}
