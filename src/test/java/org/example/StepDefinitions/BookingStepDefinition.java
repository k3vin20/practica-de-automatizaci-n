package org.example.StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.example.Questions.ResponseCode;
import org.example.Tasks.DeleteBooking;
import org.example.Tasks.GetAllBookings;
import org.example.Tasks.PostBooking;
import org.example.Tasks.PutBooking;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class BookingStepDefinition {

    @Before
    public void setTheStage(){
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el {actor} establece el endpoint de booking")
    public void elActorEstableceElEndpointDeBooking(Actor actor) {
        actor.whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"));
    }

    @When("el {actor} realiza una solicitud GET")
    public void elActorRealizaUnaSolicitudGET(Actor actor) {
        actor.attemptsTo(GetAllBookings.fromEndpoint("/booking"));
    }

    @Then("el codigo de respuesta debe ser {int}")
    public void elCodigoDeRespuestaDebeSer(int responseCode) {
        theActorInTheSpotlight().should(seeThat("El codigo de respuesta ", ResponseCode.getStatus(), equalTo(responseCode)));
    }

    @When("el actor crea un booking con el {string} {string} {string} {string} {string} {string} {string}")
    public void elActorCreaUnBookingConEl(String firtsname, String lastname, String totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) {
        theActorInTheSpotlight().attemptsTo(PostBooking.fromPage(firtsname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds));
    }

    @When("el actor actualiza el booking con {string} {string} {string} {string} {string} {string} {string}")
    public void elActorActualizaElBookingCon(String firtsname, String lastname, String totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) {
        theActorInTheSpotlight().attemptsTo(PutBooking.fromPage(firtsname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds));
    }

    @When("el {actor} elimina el booking")
    public void elActorEliminaElBooking(Actor actor) {
        actor.attemptsTo(DeleteBooking.fromPage());
    }
}