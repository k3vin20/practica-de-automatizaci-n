package org.example.StepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.example.Questions.AuthToken;
import org.example.Tasks.PostAuth;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;


import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class AuthStepDefinition {
    @Given("el {actor} establece el endpoint de auth")
    public void elActorEstableceElEndpointDeAuth(Actor actor) {
        actor.whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"));
    }

    @When("el actor solicita autenticacion como usuario {string} y password {string}")
    public void elActorSolicitaAutenticacionComoUsuarioYPassword(String username, String password) {
        theActorInTheSpotlight().attemptsTo(PostAuth.generateToken(username, password));
    }

    @Then("la respuesta debe contener un token")
    public void laRespuestaDebeContenerUnToken() {
        theActorInTheSpotlight().should(seeThat("Token generado", AuthToken.value(), notNullValue()));
    }

    @Then("la respuesta no debe contener un token")
    public void laRespuestaNoDebeContenerUnToken() {
        theActorInTheSpotlight().should(seeThat("Token generado", AuthToken.value(), nullValue()));
    }
}