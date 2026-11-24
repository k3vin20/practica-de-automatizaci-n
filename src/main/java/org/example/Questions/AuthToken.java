package org.example.Questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class AuthToken implements Question<String> {

    public static Question<String> value() {
        return new AuthToken();
    }

    @Override
    public String answeredBy(Actor actor){
        return SerenityRest.lastResponse().jsonPath().getString("token");
    }
}