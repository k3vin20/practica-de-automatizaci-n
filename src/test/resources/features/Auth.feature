@Auth
Feature: Auth from restful-booker

  @CP01_Auth
  Scenario: Generar token con credenciales validas
    Given el actor establece el endpoint de auth
    When el actor solicita autenticacion como usuario "admin" y password "password123"
    Then el codigo de respuesta debe ser 200
    And la respuesta debe contener un token

  @CP02_Auth @Negativo_Auth
  Scenario: Intentar autenticacion con credenciales invalidas
    Given el actor establece el endpoint de auth
    When el actor solicita autenticacion como usuario "asd" y password "badpassword"
    Then el codigo de respuesta debe ser 200
    And la respuesta no debe contener un token