package controllers;

import jobs.Fetch;
import models.User;
import play.Logger;
import play.libs.Json;
import play.data.Form;
import play.mvc.*;

import models.Channel;
import models.Item;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Channels extends Controller {

    public static Result all() {
        List<Channel> channels = Channel.find.all();
        int channels_count = channels.size();
        for(int i = 0; i < channels_count; i++) {
            channels.get(i).updateUnread();
        }
        return ok(Json.toJson(channels));
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

    public static Result get(Long id) {
        Channel channel = Channel.find.byId(id);
        channel.updateUnread();
        // Logger.info(channel.items);

        return ok(Json.toJson(channel));
    }

    public static Result mainView() {


//        final AuthUser currentAuthUser = PlayAuthenticate.getUser(session());
//        final User localUser = User.findByAuthUserIdentity(currentAuthUser);
//
//        if (localUser == null) {
//            return ok(Json.toJson("Please login."));
//        }

        // List<Channel> channels = Channel.find.fetch("items").findList();

        // Iterator<Channel> chan_it = channels.iterator();
        // while (chan_it.hasNext()) {
        //     Logger.debug("Number of items taken: " + chan_it.next().items.size());
        // }

        // Form<Channel> myForm = Form.form(Channel.class);
        return ok();
    }
}
