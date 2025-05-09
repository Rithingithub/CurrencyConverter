# Currency Converter API 💰

A Spring Boot application for currency conversion using Open Exchange Rates API.

## Features ✨

- Real-time currency conversion
- Exchange rate lookup
- REST API endpoints
- Swagger documentation
- Docker support

## Prerequisites 📋

- Java 17
- Maven 3.8+
- Docker (optional)
- Open Exchange Rates API key ([Get one here](https://openexchangerates.org/signup))

## Quick Start 🚀

### 1. Traditional Local Setup

```bash
https://github.com/Rithingithub/CurrencyConverter.git
cd CurrencyConverter
```

### 2. Add your API key

```
echo "currency.api.key=<your_api_key_here>" > src/main/resources/application.properties
```

### 3. Build and run

```
mvn spring-boot:run
```

## Run with Docker

```
git checkout deploy

# build image
docker build -t currency-converter .

# Run the container
docker run -d -p 8080:8080 -e CURRENCY_API_KEY=your_api_key_here currency-converter
```

## Check API

`http://localhost:8080/api/rates?base=USD`

## API Documentation 📖

After starting the application, access Swagger UI at:

`http://localhost:8080/swagger-ui/index.html`

## Branches

main: Primary development branch (run locally)

deploy: Docker-optimized version
