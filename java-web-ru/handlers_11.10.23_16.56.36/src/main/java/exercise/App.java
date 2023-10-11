package exercise;

import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;


public final class App {

    public static Javalin getApp() {

        // BEGIN
        var mapper = JavalinJackson.defaultMapper();

        try (var app = Javalin.create(config -> config.plugins.enableDevLogging())) {
            app.get("/phones", ctx -> ctx.result(mapper.writeValueAsString(Data.getPhones())));
            app.get("/domains", ctx -> ctx.result(mapper.writeValueAsString(Data.getDomains())));

            return app;
        }
    }
    // END

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
