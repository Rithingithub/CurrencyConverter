package com.example.currencyconverter;

import com.example.currencyconverter.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void testConvertCurrency() {
        double currencyConvertValue = currencyService.convertCurrency("USD", "EUR", 100);
        assertNotEquals(currencyConvertValue, 0);
    }

    @Test
    public void testGetExchangeRates() {
        Double currencyConvertValue = 1.0;
        assertEquals(currencyConvertValue, currencyService.getExchangeRates("USD").get("USD"));
    }
}