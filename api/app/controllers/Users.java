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
//        Form<User> userForm = Form.form(User.class);
//        User user = userForm.bindFromRequest().get();
        User user = User.create(values.get("email").asText(), values.get("password").asText());
        return ok(Json.toJson(user));
//        return (ok("Got json: " + request().body().asJson().get("email")));
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
            return ok(user.token);
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
