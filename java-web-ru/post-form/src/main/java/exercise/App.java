package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Collections;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        // BEGIN
        app.get("/users/build", ctx -> ctx.render("users/build.jte"));
        app.post("/users", ctx -> {
            var firstName = ctx.formParam("firstName");
            var lastName = ctx.formParam("lastName");
            var email = ctx.formParam("email");
            var password = ctx.formParam("password");

            String encryptedPsw = null;
            String normalizedEmail = null;
            String capitalizedFirstName = null;
            String capitalizedLastName = null;

            if (email != null) {
                normalizedEmail = email.toLowerCase().trim();
            }
            if (password != null) {
                encryptedPsw = Security.encrypt(password);
            }
            if (firstName != null) {
                capitalizedFirstName = StringUtils.capitalize(firstName);
            }
            if (lastName != null) {
                capitalizedLastName =  StringUtils.capitalize(lastName);
            }

            var user = new User(capitalizedFirstName, capitalizedLastName, normalizedEmail, encryptedPsw);
            UserRepository.save(user);
            ctx.redirect("/users");
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
