package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import play.libs.Json;
import play.data.Form;
import play.mvc.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by julien on 9/21/14.
 */
public class Users extends Controller {
    public static Result create() {
        final JsonNode values = request().body().asJson();
        final String email = values.get("email").asText();
        final String passowrd = values.get("password").asText();
        if (email.contains("@") && email.length() >= 6) {
            if (passowrd.length() >= 6) {
                User user = User.create(values.get("email").asText(), values.get("password").asText());
                return ok(Json.toJson(user));
            }
        }
        return badRequest(Json.toJson("Please enter a valid email and a password of at least 6characters"));
    }

    public static Result all() {
        List<User> users = User.find.all();
        return ok(Json.toJson(users));
    }

    public static Result delete(Long id) {
        User.find.ref(id).delete();
        return ok();
    }

    public static Result login() {
        final JsonNode values = request().body().asJson();
        String email = values.get("email").asText();
        String password = values.get("password").asText();
        User user = User.find.where()
                .eq("email", email)
                .findUnique();

        String hashed_password = utils.Hasher.hash(password);
        if (hashed_password.equals(user.password)) {
            if (user.token != "" || user.max_token_validity.after(new Date())) {
                user.getToken();
            }
            return ok(Json.toJson(user));
        } else {
            return(badRequest());
        }
    }

    public static Result logout(Long user_id) {
        User user = User.find.byId(user_id);
        user.deleteToken();
        return ok("ok");
    }
}
