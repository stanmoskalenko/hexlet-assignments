package exercise.dto.users;

import exercise.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

// BEGIN
@Getter
@AllArgsConstructor
public class UsersPage {
    public List<User> page;
    public String term;

    public UsersPage filter(String term) {
        var filteredUsers = page.stream()
                .filter(user -> {
                            var normalizeTerm = StringUtils.lowerCase(term);
                            var normalizeUserName = StringUtils.lowerCase(
                                    user.getFirstName()
                                    + " "
                                    + user.getLastName());
                            return normalizeUserName.contains(normalizeTerm);
                        }
                )
                .toList();
        return new UsersPage(filteredUsers, term);
    }
}
// END
