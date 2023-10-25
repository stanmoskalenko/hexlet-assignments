package exercise.dto.posts;

import exercise.dto.BasePage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildPostPage extends BasePage {
    private String name;
    private String body;
    private String error;
}
