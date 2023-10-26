package exercise.controller.users;

import exercise.Data;
import exercise.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    public static List<Post> posts = Data.getPosts();

    @GetMapping(path = "users/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> show(@PathVariable int id) {
        return posts.stream()
                .filter(entity -> entity.getUserId() == id)
                .toList();
    }

    @PostMapping(path = "users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable int id,
                       @RequestBody Post data) {
        var post = new Post();
        post.setBody(data.getBody());
        post.setSlug(data.getSlug());
        post.setUserId(id);
        post.setTitle(data.getTitle());
        posts.add(post);

        return post;
    }
}
// END
