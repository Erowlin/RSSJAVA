package controllers;

import models.Channel;
import models.Item;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import play.data.Form;
import play.api.data.*;

/**
 * Created by julien on 10/11/14.
 */
public class Items extends Controller {
    public static Result all(Long channel_id) {
        List<Item> items = Channel.find.byId(channel_id).items;
        return ok(Json.toJson(items));
    }

    public static Result get(Long channel_id, Long item_id) {
        Item item = Item.find.byId(item_id);
        return ok(Json.toJson(item));
    }

    public static Result setRead(Long channel_id, Long item_id, Boolean read) {
    	Item item = Item.find.byId(item_id);
    	item.read = read;
    	item.save();
    	return ok(Json.toJson(item));
    }
}
