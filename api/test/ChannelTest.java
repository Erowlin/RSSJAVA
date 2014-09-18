import models.Channel;
import org.junit.*;

import play.mvc.*;
import play.test.*;

import java.util.HashMap;
import java.util.Map;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import controllers.Channels;

public class ChannelTest {
    public static FakeApplication app;

    @BeforeClass
    public static void startApp() {
        app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
        Helpers.start(app);
    }

    @AfterClass
    public static void stopApp() {
        Helpers.stop(app);
    }

    @Test
    public void testCreate() {
        Map<String, String> params = new HashMap();
        params.put("title", "All Things v9");
        params.put("link", "http://anthonyeden.com/rss.xml");
        params.put("description", "Words from Anthony Eden, Founder of DNSimple");
        Result result = callAction(
            controllers.routes.ref.Channels.create(),
            new FakeRequest(POST, "/channels/create").withFormUrlEncodedBody(params)
        );
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("application/json");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("Anthony");
    }

    @Test
    public void testAll() {
        models.Channel channel = new models.Channel();
        channel.title = "All Things v9";
        channel.link = "http://anthonyeden.com/rss.xml";
        channel.description = "Words from Anthony Eden, Founder of DNSimple";
        channel.save();
        Result result = callAction(
                controllers.routes.ref.Channels.all(),
                new FakeRequest(GET, "/channels")
        );
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("application/json");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("Anthony");
    }

    @Test
    public void testDelete() {
        models.Channel channel = Channel.find.all().get(0);
        Result result = callAction(
                controllers.routes.ref.Channels.delete(channel.id),
                new FakeRequest(GET, "/channels")
        );
        models.Channel channel2 = Channel.find.byId(channel.id);
        assertThat(channel2).isNull();
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentAsString(result)).isEmpty();
    }
}
