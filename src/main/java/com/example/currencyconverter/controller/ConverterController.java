package com.example.currencyconverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.currencyconverter.model.ConversionRequest;
import com.example.currencyconverter.model.ConversionResponse;
import com.example.currencyconverter.service.CurrencyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class ConverterController {
	
	@Autowired
    CurrencyService currencyService;


    @GetMapping("/rates")
    public ResponseEntity<Map<String, Double>> getRates(@RequestParam(defaultValue = "USD") String base) {
        return ResponseEntity.ok(currencyService.getExchangeRates(base));
    }

    @PostMapping("/convert")
    public ResponseEntity<ConversionResponse> convert(@RequestBody ConversionRequest request) {
        double convertedAmount = currencyService.convertCurrency(request.getFrom(), request.getTo(), request.getAmount());
        return ResponseEntity.ok(new ConversionResponse(request.getFrom(), request.getTo(), request.getAmount(), convertedAmount));
    }
}
