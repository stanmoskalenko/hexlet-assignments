package exercise;

import io.javalin.Javalin;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            ctx.contentType("application/json");
            ctx.fullUrl();
            var companyId = ctx.pathParam("id");
            var result = COMPANIES.stream()
                    .filter(company -> company.get("id").equals(companyId))
                    .findFirst();
            if (result.isPresent()) {
                ctx.json(result.get());
            } else {
                ctx.status(404);
                ctx.result("Company not found");
//                NOTE:
//                      Тесты не проходят, т.к. ожидается сообщение в теле запроса
//                      throw new NotFoundResponse("Company not found");
            }
        });
        // END

        app.get("/companies", ctx -> ctx.json(COMPANIES));

        app.get("/", ctx -> ctx.result("open something like (you can change id): /companies/5"));

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
