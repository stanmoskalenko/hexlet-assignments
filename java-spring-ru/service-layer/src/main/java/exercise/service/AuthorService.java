package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper mapper;

    private static final String NOT_FOUND_MSG = "Not found: %s";

    public AuthorDTO findById(Long id) {
        var author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));
        return mapper.map(author);
    }

    public List<AuthorDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::map)
                .toList();
    }

    public AuthorDTO update(AuthorUpdateDTO data, Long id) {
        var author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));
        mapper.update(data, author);
        repository.save(author);

        return mapper.map(author);
    }

    public AuthorDTO create(AuthorCreateDTO data) {
        var author = mapper.map(data);
        repository.save(author);

        return mapper.map(author);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
