package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostAuth implements Task {
    private final String username, password;

    public PostAuth(String username, String password){
        this.username = username;
        this.password = password;
    }

    public static Performable generateToken(String username, String password){
        return instrumented(PostAuth.class, username, password);
    }

    @Override
    public <T extends Actor>void performAs(T actor){
        actor.attemptsTo(Post.to("/auth").with(requestSpecification ->
                requestSpecification.contentType(ContentType.JSON).
                        body("{\"username\":\"" + username + "\","
                                + "\"password\":\"" + password + "\"}")
                        .log().all()));

        SerenityRest.lastResponse().body().prettyPrint();
    }
}