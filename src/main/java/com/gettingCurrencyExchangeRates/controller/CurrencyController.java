package com.gettingCurrencyExchangeRates.controller;

import com.gettingCurrencyExchangeRates.model.Currency;
import com.gettingCurrencyExchangeRates.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/loadData")
    public String loadData(@RequestParam("date") String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        if (currencyService.loadDataForDate(parsedDate)) {
            return "Data loaded successfully for date: " + parsedDate;
        } else {
            return "Failed to load data for date: " + parsedDate;
        }
    }

    @GetMapping("/getCurrency")
    public Currency getCurrency(@RequestParam("date") String date, @RequestParam("currencyId") Long currencyId) {
        LocalDate parsedDate = LocalDate.parse(date);
        return currencyService.getCurrencyByDateAndId(parsedDate, currencyId);
    }
}
