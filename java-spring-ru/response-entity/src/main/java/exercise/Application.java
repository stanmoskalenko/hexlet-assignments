package exercise;

import exercise.model.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Post>> index(
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "1") Integer page) {
        var resultPage = getPage(limit, page);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(resultPage);
    }

    @GetMapping(path = "/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        var body = posts.stream()
                .filter(entity -> entity.getId().equals(id))
                .findAny()
                .orElse(null);
        if (body == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(body);
    }

    @PutMapping(path = "/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id,
                                       @RequestBody Post data) {
        var maybePage = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();
        if (maybePage.isPresent()) {
            var page = maybePage.get();
            page.setBody(data.getBody());
            page.setTitle(data.getTitle());

            return ResponseEntity.ok().body(maybePage.get());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(data);
    }

    @PostMapping(path = "/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
