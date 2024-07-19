package com.gettingCurrencyExchangeRates.service;

import com.gettingCurrencyExchangeRates.model.Currency;
import com.gettingCurrencyExchangeRates.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

/**
 * Service for retrieving currency exchange rates.
 */
@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * Loads currency exchange rate data for a specific date.
     *
     * @param date The date for which to load currency exchange rate data.
     * @return true if the data is successfully loaded, false otherwise.
     */
    public boolean loadDataForDate(LocalDate date) {
        String apiUrl = "https://www.nbrb.by/api/exrates/rates?ondate=" + date.toString() + "&periodicity=0";
        RestTemplate restTemplate = new RestTemplate();
        Currency[] currencyRates = restTemplate.getForObject(apiUrl, Currency[].class);

        if (currencyRates != null) {
            for (Currency rate : currencyRates) {
                Currency currency = new Currency();
                currency.setId(rate.getId());
                currency.setDate(rate.getDate());
                currency.setAbbreviation(rate.getAbbreviation());
                currency.setScale(rate.getScale());
                currency.setName(rate.getName());
                currency.setRate(rate.getRate());
                currencyRepository.save(currency);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves information about the currency exchange rate for a specific date and currency ID.
     *
     * @param date        The date for which to retrieve information about the currency exchange rate.
     * @param currencyId  The ID of the currency.
     * @return A Currency object with information about the currency exchange rate for the specified date and currency ID.
     */
    public Currency getCurrencyByDateAndId(LocalDate date, Long currencyId) {
        return currencyRepository.getCurrencyByDateAndId(date, currencyId);
    }
}
