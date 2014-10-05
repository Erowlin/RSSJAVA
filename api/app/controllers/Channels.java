package controllers;

import jobs.Fetch;
import play.Logger;
import play.libs.Json;
import play.data.Form;
import play.mvc.*;

import models.Channel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Channels extends Controller {

    public static Result all() {
        List<Channel> users = Channel.find.all();
        return ok(Json.toJson(users));
    }

    public static Result create() {
        Form<Channel> channelForm = Form.form(Channel.class);
        Channel c = channelForm.bindFromRequest().get();
        Fetch.runOne(c);
        return ok(Json.toJson(c));
    }

    public static Result delete(Long id) {
        Channel.find.ref(id).delete();
        return ok();
    }


    public static Result mainView() {
        List<Channel> users = Channel.find.fetch("items").findList();
        Form<Channel> myForm = Form.form(Channel.class);
        return ok(views.html.index.render(users, myForm));
    }
}
