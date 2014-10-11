package controllers;

import models.Channel;
import models.Item;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by julien on 10/11/14.
 */
public class Items extends Controller {
    public static Result all(Long channel_id) {
        List<Item> items = Channel.find.byId(channel_id).items;
        return ok(Json.toJson(items));
    }

    public static Result get(Long channel_id, Long item_id) {
        return ok();
    }
}
