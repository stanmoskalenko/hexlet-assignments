package exercise.controller;

import exercise.dto.CommentDTO;
import exercise.dto.PostDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;


    private List<CommentDTO> toDTO(List<Comment> comments) {
        return comments.stream()
                .map(comment -> {
                    var dto = new CommentDTO();
                    dto.setId(comment.getId());
                    dto.setBody(comment.getBody());
                    return dto;
                })
                .toList();
    }

    private PostDTO toDTO(Post post, List<CommentDTO> comments) {
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        dto.setComments(comments);
        return dto;
    }

    @GetMapping
    private List<PostDTO> index() {
        return postRepository.findAll().stream()
                .map(post -> {
                    var comments = this.toDTO(commentRepository.findByPostId(post.getId()));
                    return this.toDTO(post, comments);
                })
                .toList();
    }

    @GetMapping(path = "/{id}")
    private PostDTO show(@PathVariable Long id) {
        return postRepository.findById(id).stream()
                .map(post -> {
                    var comments = this.toDTO(commentRepository.findByPostId(post.getId()));
                    return this.toDTO(post, comments);
                })
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }
}
// END
