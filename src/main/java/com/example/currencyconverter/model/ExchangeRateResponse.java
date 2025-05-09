package com.example.currencyconverter.model;

import java.util.Map;

public class ExchangeRateResponse {
    private String base;
    private Map<String, Double> rates;
    private String error;
	private Integer status;
    private String message;
    private String description;
    
    
    public String getBase() {
		return base;
	}
	public Map<String, Double> getRates() {
		return rates;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

    
}
