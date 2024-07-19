package com.gettingCurrencyExchangeRates.repository;

import com.gettingCurrencyExchangeRates.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

/**
 * Repository for accessing currency data.
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    /**
     * Retrieves information about the currency exchange rate for a specific date and currency ID.
     *
     * @param date        The date for which to retrieve information about the currency exchange rate.
     * @param currencyId  The ID of the currency.
     * @return A Currency object with information about the currency exchange rate for the specified date and currency ID.
     */
    Currency getCurrencyByDateAndId(LocalDate date, Long currencyId);
}
