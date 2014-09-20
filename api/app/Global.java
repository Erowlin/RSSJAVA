import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
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
        FiniteDuration delay = FiniteDuration.create(0, TimeUnit.SECONDS);
        FiniteDuration frequency = FiniteDuration.create(60, TimeUnit.SECONDS);
        Akka.system().scheduler().schedule(delay, frequency, new Runnable() {
            @Override
            public void run() {
                Fetch.run();
            }
        }, Akka.system().dispatcher());
    }

}