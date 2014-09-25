package controllers;

import play.mvc.*;
import views.html.*;

public class Application extends Controller {
    public static Result index() {
        return ok("coucou");
    }
    public static Result oAuthDenied(final String providerKey) {
        return ok("coucou");
    }
}
