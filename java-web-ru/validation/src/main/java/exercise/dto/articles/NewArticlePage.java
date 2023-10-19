package exercise.dto.articles;

import io.javalin.validation.ValidationError;
import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewArticlePage {
    private String title;
    private String content;
    private Map<String, List<ValidationError<Object>>> errors;
}
