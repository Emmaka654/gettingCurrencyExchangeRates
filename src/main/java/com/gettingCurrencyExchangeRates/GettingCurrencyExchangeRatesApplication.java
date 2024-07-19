package com.gettingCurrencyExchangeRates;

import com.gettingCurrencyExchangeRates.controller.CurrencyController;
import com.gettingCurrencyExchangeRates.model.Currency;
import com.gettingCurrencyExchangeRates.repository.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
            while (!isValidDate(date)) {
                System.out.println("You entered date is the wrong format. Please, enter in format 'yyyy-mm-dd' and make sure that your date is no earlier than 2000-01-01 and no later than tomorrow");
                date = in.next();
            }
            makeListOfCurrencies(currencyRepository, currencyController, date);
            System.out.print("Enter the currency ID: ");
            Long inputCurrrencyId = in.nextLong();
            while (currencyRepository.findById(inputCurrrencyId).isEmpty()) {
                System.out.println("You have entered a non-existent ID. Please enter the correct one.");
                inputCurrrencyId = in.nextLong();
            }
            System.out.println("Information about currency " + inputCurrrencyId + " on date " + date + ":");
            System.out.println(currencyController.getCurrency(date, inputCurrrencyId));
            in.close();
        };
    }

    private static void makeListOfCurrencies(CurrencyRepository currencyRepository, CurrencyController currencyController, String date) {
        System.out.println("The url is https://www.nbrb.by/api/exrates/rates?ondate=" + date + "&periodicity=0");
        System.out.println(currencyController.loadData(date));
        List<Currency> currencies = currencyRepository.findAll();
        System.out.println("The list of currencies on date " + date + ":");
        for (Currency currency : currencies) {
            System.out.println();
            System.out.println("ID: " + currency.getId());
            System.out.println("Date: " + currency.getDate());
            System.out.println("Abbreviation: " + currency.getAbbreviation());
            System.out.println("Scale: " + currency.getScale());
            System.out.println("Name: " + currency.getName());
            System.out.println("Rate: " + currency.getRate());
            System.out.println();
        }
    }

    public boolean isValidDate(String inputDate) {
        try {
            LocalDate date = LocalDate.parse(inputDate);
            LocalDate minDate = LocalDate.of(2000, 1, 1);
            LocalDate maxDate = LocalDate.now().plusDays(1);

            return (date.isAfter(minDate) || date.isEqual(minDate)) && date.isBefore(maxDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
