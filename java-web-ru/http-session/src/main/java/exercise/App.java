package exercise;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static List<Map<String, String>> getPage(Integer pageNumber, Integer itemsCount) {
        List<Map<String, String>> result = new ArrayList<>();
        int offset;

        if (pageNumber == 1 || pageNumber == 0) {
            offset = 0;
        } else {
            offset = (pageNumber - 1) * itemsCount;
        }

        if (offset > 30) {
            result.addAll(USERS);
        } else if (offset + itemsCount > 30) {
            var lastItems = 30 - offset;
            var users = USERS.subList(offset, offset + lastItems);
            result.addAll(users);
        } else if (offset + itemsCount <= 30) {
            var idxItems = offset + itemsCount;
            var users = USERS.subList(offset, idxItems);
            result.addAll(users);
        }

        return result;
    }

    public static Javalin getApp() {
        try (var app = Javalin.create(config -> config.plugins.enableDevLogging())) {
            app.get("/users", ctx -> {
                        var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
                        var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
                        var users = getPage(page, per);
                        ctx.contentType("application/json");
                        ctx.fullUrl();
                        ctx.json(users);
                    }
            );

            return app;
        }
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
