package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<Void> destroy(Integer id) {
        return userRepository.deleteById(id);
    }

    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    public Mono<User> update(Integer id, User data) {
        return Mono.just(id)
                .flatMap(userRepository::findById)
                .flatMap(user -> {
                    user.setEmail(data.getEmail());
                    user.setFirstName(data.getFirstName());
                    user.setLastName(data.getLastName());

                    return userRepository.save(user);
                });
    }

    public Mono<User> show(Integer id) {
        return userRepository.findById(id);
    }
}
