package exercise;

import exercise.model.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private static List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    public static List<Post> getPage(Integer limit, Integer pageNumber) {
        List<Post> result = new ArrayList<>();
        var postsCount = posts.size();
        var offset = 0;

        if (pageNumber > 1) {
            offset = (pageNumber - 1) * limit;
        }

        if (offset > postsCount) {
            result.addAll(posts);
        } else if (offset + limit > postsCount) {
            var lastItems = limit - offset;
            var page = posts.subList(offset, offset + lastItems);
            result.addAll(page);
        } else if (offset + limit <= postsCount) {
            var idxItems = offset + limit;
            var page = posts.subList(offset, idxItems);
            result.addAll(page);
        }

        return result;
    }

    @GetMapping(path = "/posts")
    public List<Post> index(
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "1") Integer page) {
        return getPage(limit, page);
    }

    @GetMapping(path = "/posts/{id}")
    public Post show(@PathVariable String id) {
        return posts.stream()
                .filter(entity -> entity.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @PutMapping(path = "/posts/{id}")
    public Post update(@PathVariable String id,
                       @RequestBody Post data) {
        var maybePage = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();
        if (maybePage.isPresent()) {
            var page = maybePage.get();
            page.setBody(data.getBody());
            page.setTitle(data.getTitle());
        }
        return data;
    }

    @PostMapping(path = "/posts")
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @DeleteMapping(path = "/posts/{id}")
    public Boolean delete(@PathVariable String id) {
        return posts.removeIf(post -> post.getId().equals(id));
    }

    // END
}
