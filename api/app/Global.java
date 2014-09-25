import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.exceptions.AccessDeniedException;
import com.feth.play.module.pa.exceptions.AuthException;
import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import play.Application;
import play.GlobalSettings;
import play.mvc.Call;
import scala.concurrent.duration.FiniteDuration;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jobs.Fetch;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        PlayAuthenticate.setResolver(new PlayAuthenticate.Resolver() {

            @Override
            public Call login() {
                // Your login page
                return controllers.routes.Application.index();
            }

            @Override
            public Call afterAuth() {
                // The user will be redirected to this page after authentication
                // if no original URL was saved
                return controllers.routes.Application.index();
            }

            @Override
            public Call afterLogout() {
                return controllers.routes.Application.index();
            }

            @Override
            public Call auth(final String provider) {
                // You can provide your own authentication implementation,
                // however the default should be sufficient for most cases
                return com.feth.play.module.pa.controllers.routes.Authenticate
                        .authenticate(provider);
            }

            @Override
            public Call onException(final AuthException e) {
                if (e instanceof AccessDeniedException) {
                    return controllers.routes.Application
                            .oAuthDenied(((AccessDeniedException) e)
                                    .getProviderKey());
                }

                // more custom problem handling here...

                return super.onException(e);
            }

            @Override
            public Call askLink() {
                // We don't support moderated account linking in this sample.
                // See the play-authenticate-usage project for an example
                return null;
            }

            @Override
            public Call askMerge() {
                // We don't support moderated account merging in this sample.
                // See the play-authenticate-usage project for an example
                return null;
            }
        });
//        FiniteDuration delay = FiniteDuration.create(0, TimeUnit.SECONDS);
//        FiniteDuration frequency = FiniteDuration.create(60, TimeUnit.SECONDS);
//        Akka.system().scheduler().schedule(delay, frequency, new Runnable() {
//            @Override
//            public void run() {
//                Fetch.run();
//            }
//        }, Akka.system().dispatcher());
    }

}