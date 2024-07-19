package com.gettingCurrencyExchangeRates;

import com.gettingCurrencyExchangeRates.controller.CurrencyController;
import com.gettingCurrencyExchangeRates.model.Currency;
import com.gettingCurrencyExchangeRates.repository.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class GettingCurrencyExchangeRatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GettingCurrencyExchangeRatesApplication.class, args);

    }

    @Bean
    public CommandLineRunner test(CurrencyRepository currencyRepository, CurrencyController currencyController) {
        return (args) -> {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter date in format 'yyyy-mm-dd': ");
            String date = in.next();
            System.out.println("The url is https://www.nbrb.by/api/exrates/rates?ondate=" + date + "&periodicity=0");
            System.out.println(currencyController.loadData(date));
            List<Currency> currencies = currencyRepository.findAll();
            System.out.println("The list of currencies on date " + date + ":");
            for (Currency currency : currencies) {
                System.out.println("ID: " + currency.getId());
                System.out.println("Date: " + currency.getDate());
                System.out.println("Abbreviation: " + currency.getAbbreviation());
                System.out.println("Scale: " + currency.getScale());
                System.out.println("Name: " + currency.getName());
                System.out.println("Rate: " + currency.getRate());
                System.out.println();
            }
            System.out.print("Enter the currency ID: ");
            Long inputCurrrencyId = in.nextLong();
            System.out.println("Information about currency " + inputCurrrencyId + " on date " + date + ":");
            System.out.println(currencyController.getCurrency(date, inputCurrrencyId));
            in.close();
        };
    }

}
