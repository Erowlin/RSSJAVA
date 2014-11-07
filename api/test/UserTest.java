import models.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;
import play.test.Helpers;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

/**
 * Created by julien on 9/21/14.
 */
public class UserTest {
    public static FakeApplication app;
//
//    @BeforeClass
//    public static void startApp() {
//        app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
//        Helpers.start(app);
//    }
//
//    @AfterClass
//    public static void stopApp() {
//        Helpers.stop(app);
//    }
//
//    @Test
//    public void testCreate() {
//        Map<String, String> params = new HashMap();
//        params.put("email", "test@rss.org");
//        params.put("password", "coucou42");
//        Result result = callAction(
//                controllers.routes.ref.Users.create(),
//                new FakeRequest(POST, "/users/create").withFormUrlEncodedBody(params)
//        );
//        assertThat(User.authenticate("test@rss.org", "coucou42")).isNotNull();
//    }
//
//    @Test
//    public void testAll() {
//        User user = new User();
//        user.email = "test2@rss.org";
//        user.password = "coucou42";
//        user.hashPassword();
//        user.save();
//        Result result = callAction(
//                controllers.routes.ref.Users.all(),
//                new FakeRequest(GET, "/users")
//        );
//        assertThat(status(result)).isEqualTo(OK);
//        assertThat(contentType(result)).isEqualTo("application/json");
//        assertThat(charset(result)).isEqualTo("utf-8");
//        assertThat(contentAsString(result)).contains("test2@rss.org");
//    }
//
//    @Test
//    public void testDelete() {
//        User user = User.find.all().get(0);
//        Result result = callAction(
//                controllers.routes.ref.Users.delete(user.id),
//                new FakeRequest(GET, "/users")
//        );
//        User user2 = User.find.byId(user.id);
//        assertThat(user2).isNull();
//        assertThat(status(result)).isEqualTo(OK);
//        assertThat(contentAsString(result)).isEmpty();
//    }
//
//    public void addChanelToUser() {
//        User user = new User();
//        user.email = "test2@rss.org";
//        user.password = "coucou42";
//        user.hashPassword();
//        user.save();
//
//        Map<String, String> params = new HashMap();
//        params.put("email", "test@rss.org");
//        params.put("password", "coucou42");
//        Result result = callAction(
//                controllers.routes.ref.Users.create(),
//                new FakeRequest(POST, "/users/create").withFormUrlEncodedBody(params)
//        );
//
//    }
}
