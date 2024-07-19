package com.gettingCurrencyExchangeRates.controller;

import com.gettingCurrencyExchangeRates.model.Currency;
import com.gettingCurrencyExchangeRates.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Controller for handling currency-related HTTP requests.
 */
@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    /**
     * Loads currency exchange rate data for a specific date.
     *
     * @param date The date for which to load currency exchange rate data in the format "yyyy-mm-dd".
     * @return A message indicating the success or failure of the data loading process.
     */
    @GetMapping("/loadData")
    public String loadData(@RequestParam("date") String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        if (currencyService.loadDataForDate(parsedDate)) {
            return "Data loaded successfully for date: " + parsedDate;
        } else {
            return "Failed to load data for date: " + parsedDate;
        }
    }

    /**
     * Retrieves information about the currency exchange rate for a specific date and currency ID.
     *
     * @param date        The date for which to retrieve information about the currency exchange rate in the format "yyyy-mm-dd".
     * @param currencyId  The ID of the currency.
     * @return A Currency object with information about the currency exchange rate for the specified date and currency ID.
     */
    @GetMapping("/getCurrency")
    public Currency getCurrency(@RequestParam("date") String date, @RequestParam("currencyId") Long currencyId) {
        LocalDate parsedDate = LocalDate.parse(date);
        return currencyService.getCurrencyByDateAndId(parsedDate, currencyId);
    }
}
