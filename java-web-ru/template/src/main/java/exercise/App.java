package exercise;

import exercise.dto.users.UsersPage;
import exercise.model.User;
import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {
        var app = Javalin.create(config -> config.plugins.enableDevLogging());
        app.get("/users/{id}", ctx -> {
            ctx.fullUrl();
            ctx.contentType("text/html");
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = Data.getUserById(id);
            user.ifPresent(currentUser -> ctx.render(
                    "users/show.jte",
                    Collections.singletonMap("user", currentUser)));
        });

        app.get("/users", ctx -> {
            ctx.fullUrl();
            ctx.contentType("text/html");
            var users = new UsersPage(USERS);
            ctx.render("users/index.jte", Collections.singletonMap("users", users));
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
