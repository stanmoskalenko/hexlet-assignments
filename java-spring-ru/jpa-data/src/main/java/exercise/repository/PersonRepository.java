package exercise.repository;

import exercise.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// BEGIN
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
// END
