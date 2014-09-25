package controllers;

import models.User;
import play.libs.Json;
import play.data.Form;
import play.mvc.*;

import java.util.List;

/**
 * Created by julien on 9/21/14.
 */
public class Users extends Controller {
    public static Result create() {
        Form<User> userForm = Form.form(User.class);
        User user = userForm.bindFromRequest().get();
//        user.hashPassword();
        return ok(Json.toJson(user));
    }

    public static Result all() {
        List<User> users = User.find.all();
        return ok(Json.toJson(users));
    }

    public static Result delete(Long id) {
        User.find.ref(id).delete();
        return ok();
    }
}
