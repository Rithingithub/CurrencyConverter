package com.example.currencyconverter.service;

import com.example.currencyconverter.exceptions.CurrencyNotFoundException;
import com.example.currencyconverter.model.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyService {
    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String apiUrl;

    public CurrencyService(RestTemplate restTemplate,
            @Value("${currency.api.key}") String apiKey,
            @Value("${currency.api.url:https://openexchangerates.org/api/latest.json}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    public Map<String, Double> getExchangeRates(String baseCurrency) {
        String url = String.format("%s?app_id=%s&base=%s", apiUrl, apiKey, baseCurrency);
        try {
            ResponseEntity<ExchangeRateResponse> response = restTemplate.getForEntity(url, ExchangeRateResponse.class);
            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                throw new CurrencyNotFoundException("Failed to fetch exchange rates for currency: " + baseCurrency);
            }
            return response.getBody().getRates();
        } catch (Exception e) {
            throw new CurrencyNotFoundException("Error fetching exchange rates: " + e.getMessage());
        }
    }

    public double convertCurrency(String from, String to, double amount) {
        Map<String, Double> rates = getExchangeRates(from);
        if (!rates.containsKey(to)) {
            throw new CurrencyNotFoundException("Invalid target currency: " + to);
        }
        return amount * rates.get(to);
    }
}