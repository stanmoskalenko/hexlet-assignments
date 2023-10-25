package exercise.controller;

import exercise.dto.posts.BuildPostPage;
import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

import java.util.Collections;

public class PostsController {

    public static final String SUCCESS_ALERT_MSG = "Пост был успешно создан!";
    public static final String ERROR_MSG = "Наименование должно быть уникальным!";

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", Collections.singletonMap("page", page));
    }

    // BEGIN
    public static void index(Context ctx) {
        var posts = PostRepository.getEntities();
        var postPage = new PostsPage(posts);
        postPage.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("posts/index.jte", Collections.singletonMap("page", postPage));
    }

    public static void create(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> !PostRepository.existsByName(value), ERROR_MSG)
                    .getOrThrow(ValidationException::new);

            var body = ctx.formParamAsClass("body", String.class).get();
            var post = new Post(name, body);
            PostRepository.save(post);
            var page = new PostsPage(PostRepository.getEntities());
            page.setFlash(SUCCESS_ALERT_MSG);

            ctx.render("posts/index.jte", Collections.singletonMap("page", page)).status(302);

        } catch (ValidationException e) {
            var name = ctx.formParamAsClass("name", String.class).get();
            var body = ctx.formParamAsClass("body", String.class).get();
            var page = new BuildPostPage(name, body, ERROR_MSG);

            ctx.render("posts/build.jte", Collections.singletonMap("page", page)).status(400);
        }
    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }
}
