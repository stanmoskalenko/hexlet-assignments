package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper mapper;

    private static final String NOT_FOUND_MSG = "Not found: %s";

    public BookDTO create(BookCreateDTO data) {
        var book = mapper.map(data);
        repository.save(book);

        return mapper.map(book);
    }

    public BookDTO findById(Long id) {
        var book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));
        return mapper.map(book);
    }

    public List<BookDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::map)
                .toList();
    }

    public BookDTO update(BookUpdateDTO data, Long id) {
        var book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));
        mapper.update(data, book);
        repository.save(book);

        return mapper.map(book);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
