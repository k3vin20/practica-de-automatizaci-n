package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteBooking implements Task {

    public static Performable fromPage(){
        return instrumented(DeleteBooking.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor){

        String bookingId = actor.recall("bookingId");
        actor.attemptsTo(
                Delete.from("/booking/" + bookingId).with(requestSpecification ->
                        requestSpecification.contentType(ContentType.JSON)
                                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                                .log().all())
        );
    }

}