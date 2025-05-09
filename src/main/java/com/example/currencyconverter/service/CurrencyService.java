package com.example.currencyconverter.service;

import com.example.currencyconverter.exceptions.CurrencyNotFoundException;
import com.example.currencyconverter.model.ExchangeRateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;



@Service
public class CurrencyService {
    private final RestTemplate restTemplate;
    private final String API_KEY = System.getProperty("currency.api.key");
    private final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=" + API_KEY + "&base=";

        
    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Double> getExchangeRates(String baseCurrency) {
        String url = API_URL + baseCurrency;
        try {
        	ResponseEntity<ExchangeRateResponse> response = restTemplate.getForEntity(url, ExchangeRateResponse.class);
        	return response.getBody().getRates();
        } catch (Exception e) {
        	throw new CurrencyNotFoundException("Invalid or Access denied currency: " + e.getMessage());
        }
        
        
    }

    public double convertCurrency(String from, String to, double amount) {
        Map<String, Double> rates = getExchangeRates(from);
        if (!rates.containsKey(to)) {
            throw new CurrencyNotFoundException("Invalid currency: " + to);
        }
        return amount * rates.get(to);
    }
}
