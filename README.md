# Servicio de Autenticación JWT con Spring Boot

Este servicio proporciona autenticación basada en JWT y consumo de una API externa para obtener datos del clima.

## Requisitos Previos

- Java 17 
- Maven 3.8+
- Docker (opcional, para ejecución en contenedor)

## Configuración

1. Clona el repositorio:
git clone https://github.com/Adonay23/weatherPT.git
2. cd weatherPT


## Ejecución del servicio

1. Se debe generar el token utilizando el endPoit http://localhost:8082/api/weather/forecast
  debe incluir los siguientes parametros: 
        -username:**admin**     y     password:**password**
2. Luego se utiliza el token generado como Autorization bearer Token para el EndPoint: http://localhost:8082/api/weather/forecast
        - debe incluir el parametro **city** con la ciudad de la que se desea conocer el clima.




