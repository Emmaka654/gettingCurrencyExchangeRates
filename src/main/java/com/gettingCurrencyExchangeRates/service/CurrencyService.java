package com.gettingCurrencyExchangeRates.service;

import com.gettingCurrencyExchangeRates.model.Currency;
import com.gettingCurrencyExchangeRates.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

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

    public Currency getCurrencyByDateAndId(LocalDate date, Long currencyId) {
        return currencyRepository.getCurrencyByDateAndId(date, currencyId);
    }
}
