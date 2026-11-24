package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutBooking implements Task {
    private final String firtsname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds;
    private final String bookingId;

    public PutBooking(String firtsname, String lastname, String totalprice, String depositpaid, String checkin, String checkout, String additionalneeds) {
        this.firtsname = firtsname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.checkin = checkin;
        this.checkout = checkout;
        this.additionalneeds = additionalneeds;
        this.bookingId = OnStage.theActorInTheSpotlight().recall("bookingId");
    }

    public static Performable fromPage(String firtsname, String lastname, String totalprice, String depositpaid, String checkin, String checkout, String additionalneeds){
        return instrumented(PutBooking.class, firtsname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
    }

    @Override
    public <T extends Actor>void performAs(T actor){
        actor.attemptsTo(Put.to("/booking/"+bookingId)
                .with(requestSpecification -> requestSpecification.contentType(ContentType.JSON)
                        .header("Accept","application/json")
                        .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                        .body("""
                                {
                                    "firstname": "%s",
                                    "lastname": "%s",
                                    "totalprice": %s,
                                    "depositpaid": %s,
                                    "bookingdates": {
                                        "checkin": "%s",
                                        "checkout": "%s"
                                    },
                                    "additionalneeds": "%s"
                                }
                                """.formatted(firtsname,lastname,totalprice,depositpaid,checkin,checkout,additionalneeds)
                        ).log().all()
                )
        );
    }
}