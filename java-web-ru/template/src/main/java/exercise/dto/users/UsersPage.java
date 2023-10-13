package exercise.dto.users;

import exercise.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
// BEGIN
public class UsersPage {
    List<User> page;

}
// END
