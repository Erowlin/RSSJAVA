package controllers;

import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.user.AuthUser;
import play.*;
import play.mvc.*;
import models.User;

import views.html.*;

public class Application extends Controller {
	public static Result index() {
		final AuthUser currentAuthUser = PlayAuthenticate.getUser(session());
		final User localUser = User.findByAuthUserIdentity(currentAuthUser);
		if (localUser == null) {
			return ok(login.render());
		}
		return ok(index.render());
	}
	public static Result oAuthDenied(final String providerKey) {
		return ok("coucou");
	}
}
