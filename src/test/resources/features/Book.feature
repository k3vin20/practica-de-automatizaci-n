@BookingAll
Feature: APIS from Booking
  Como usuario de Booking
  Quiero obtener la lista de bookings
  Para poder verificar los detalles de los registros
  @CP01_Booking @Patch_Booking @Booking
  Scenario: Obtener todas los booking exitosamente
    Given el actor establece el endpoint de booking
    When el actor realiza una solicitud GET
    Then el codigo de respuesta debe ser 200

  @CP02_Booking @Patch_Booking @Booking
  Scenario Outline: Crear un booking exitosamente
    Given el actor establece el endpoint de booking
    When el actor crea un booking con el "<firstname>" "<lastname>" "<totalprice>" "<depositpaid>" "<checkin>" "<checkout>" "<additionalneeds>"
    Then el codigo de respuesta debe ser 200

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Jorge     | Flores | 212        | true        | 2018-01-01 | 2019-01-01 | Breakfast       |

  @CP03_Booking @Patch_Booking @Booking
  Scenario Outline: Actualizar datos de un booking existosamente
    Given el actor establece el endpoint de booking
    And el actor crea un booking con el "Jorge" "Flores" "212" "true" "2018-01-01" "2019-01-01" "Breakfast"
    When el actor actualiza el booking con "<firstname>" "<lastname>" "<totalprice>" "<depositpaid>" "<checkin>" "<checkout>" "<additionalneeds>"
    Then el codigo de respuesta debe ser 200

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Kevin  | Quispe  | 222        | false       | 2026-01-01 | 2026-02-01 | Lunch           |

  @CP04_Booking @Patch_Booking @Booking
  Scenario: Eliminar booking exitosamente
    Given el actor establece el endpoint de booking
    And el actor crea un booking con el "Jorge" "Flores" "212" "true" "2018-01-01" "2019-01-01" "Breakfast"
    When el actor elimina el booking
    Then el codigo de respuesta debe ser 201

  @CP05_Booking @Negativo_Booking @Booking
  Scenario: Intentar crear booking con precio invalido
    Given el actor establece el endpoint de booking
    When el actor crea un booking con el "Jorge" "Flores" "asdr" "true" "2018-01-01" "2019-01-01" "Breakfast"
    Then el codigo de respuesta debe ser 400