package exercise;

import io.javalin.Javalin;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import java.util.Collections;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();
    private static final String PAGE = "users/index.jte";

    public static Javalin getApp() {

        var app = Javalin.create(config -> config.plugins.enableDevLogging());
        // BEGIN
        app.get("users/", ctx -> {
            var term = ctx.queryParam("term");
            var users = new UsersPage(USERS, term);

            if (term != null) {
                ctx.render(PAGE, Collections.singletonMap("page", users.filter(term)));
            } else {
                ctx.render(PAGE, Collections.singletonMap("page", users));
            }
        });
        // END

        app.get("/", ctx -> ctx.render("index.jte"));

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
