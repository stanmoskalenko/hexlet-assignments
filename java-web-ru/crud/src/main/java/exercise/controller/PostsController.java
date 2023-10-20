package exercise.controller;

import java.util.*;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.util.NamedRoutes;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    private static final Integer PAGE_ITEMS_COUNT = 5;
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer POSTS_COUNT = PostRepository.getEntities().size();
    private static final Integer PAGE_COUNT = POSTS_COUNT / PAGE_ITEMS_COUNT;

    public static List<Post> getPage(Integer pageNumber) {
        List<Post> result = new ArrayList<>() {
        };
        var allPosts = PostRepository.getEntities();
        int offset;
        if (pageNumber == 1 || pageNumber == 0) {
            offset = 0;
        } else {
            offset = (pageNumber - 1) * PAGE_ITEMS_COUNT;
        }

        if (offset > POSTS_COUNT) {
            result.addAll(allPosts);
        } else if (offset + PAGE_ITEMS_COUNT > POSTS_COUNT) {
            var lastItems = PAGE_ITEMS_COUNT - offset;
            var posts = allPosts.subList(offset, offset + lastItems);
            result.addAll(posts);
        } else if (offset + PAGE_ITEMS_COUNT <= POSTS_COUNT) {
            var idxItems = offset + PAGE_ITEMS_COUNT;
            var posts = allPosts.subList(offset, idxItems);
            result.addAll(posts);
        }

        return result;
    }

    public static void index(Context ctx) {
        ctx.fullUrl();
        var pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(DEFAULT_PAGE_NUMBER);
        var postsPage = getPage(pageNumber);
        var page = new PostsPage(postsPage, PAGE_COUNT, pageNumber);
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }

}
