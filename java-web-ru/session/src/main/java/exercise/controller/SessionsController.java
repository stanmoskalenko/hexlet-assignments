package exercise.controller;

import exercise.dto.LoginPage;
import exercise.dto.MainPage;
import exercise.repository.UsersRepository;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

import java.util.Collections;

public class SessionsController {

    private static final String ERROR_MSG = "Wrong username or password";

    // BEGIN
    public static void index(Context ctx) throws Exception {
        ctx.render("index.jte");
    }

    public static void build(Context ctx) throws Exception {
        ctx.render("build.jte");
    }

    public static void login(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(UsersRepository::existsByName, ERROR_MSG)
                    .getOrThrow(ValidationException::new);

            ctx.formParamAsClass("password", String.class)
                    .check(value -> UsersRepository.findByName(name).getPassword().equals(Security.encrypt(value)), ERROR_MSG)
                    .getOrThrow(ValidationException::new);

            var user = UsersRepository.findByName(name);
            var mainPage = new MainPage(user.getName());
            ctx.sessionAttribute("currentUser", user.getName());
            ctx.render("index.jte", Collections.singletonMap("page", mainPage)).status(302);

        } catch (ValidationException e) {
            var name = ctx.formParamAsClass("name", String.class).get();
            LoginPage loginPage = new LoginPage(name, ERROR_MSG);
            ctx.render("build.jte", Collections.singletonMap("page", loginPage));
        }
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }
    // END
}
