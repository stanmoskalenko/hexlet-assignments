package exercise;

import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.NewArticlePage;
import exercise.model.Article;
import exercise.repository.ArticleRepository;
import io.javalin.Javalin;
import io.javalin.validation.ValidationError;
import io.javalin.validation.ValidationException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class App {

    private static final String UNIQUE_TITLE_ERROR_MSG = "Статья с таким названием уже существует";
    private static final String LEN_TITLE_ERROR_MSG = "Название не должно быть короче двух символов";
    private static final String LEN_CONTENT_ERROR_MSG = "Статья должна быть не короче 10 символов";


    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/articles/new", ctx -> ctx.render("articles/build.jte"));

        // BEGIN
        app.post("/articles", ctx -> {
            try {
                var title = ctx.formParamAsClass("title", String.class)
                        .check(value -> !ArticleRepository.existsByTitle(value), UNIQUE_TITLE_ERROR_MSG)
                        .check(value -> value.length() > 2, LEN_TITLE_ERROR_MSG)
                        .getOrThrow(ValidationException::new);
                var content = ctx.formParamAsClass("content", String.class)
                        .check(value -> value.length() > 10, LEN_CONTENT_ERROR_MSG)
                        .getOrThrow(ValidationException::new);

                var article = new Article(title, content);
                ArticleRepository.save(article);
                ctx.redirect("/articles");

            } catch (ValidationException e) {
                ctx.status(422);
                var title = ctx.formParam("title");
                var content = ctx.formParam("content");
                var invalidPage = new NewArticlePage(title, content, e.getErrors());
                ctx.render("articles/build.jte", Collections.singletonMap("invalidPage", invalidPage));
            }
        });
        // END
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
