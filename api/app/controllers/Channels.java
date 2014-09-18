package controllers;

import play.libs.Json;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import models.Channel;

import java.util.List;

public class Channels extends Controller {

    public static Result all() {
        List<Channel> users = Channel.find.all();
        return ok(Json.toJson(users));
    }

    public static Result create() {
        Form<Channel> userForm = Form.form(Channel.class);
        Channel channel = userForm.bindFromRequest().get();
        return ok(Json.toJson(channel));
    }

    public static Result delete(Long id) {
        Channel.find.ref(id).delete();
        return ok();
    }
}
