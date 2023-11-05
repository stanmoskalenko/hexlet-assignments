package exercise.controller;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.TaskMapper;
import exercise.repository.TaskRepository;
import exercise.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private static final String TASK_NOT_FOUND_MSG = "Task with id %s not found";
    private static final String USER_NOT_FOUND_MSG = "User with id %s not found";
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskMapper mapper;

    @GetMapping
    public List<TaskDTO> index() {
        return taskRepository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    @GetMapping(path = "/{id}")
    public TaskDTO show(@PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(TASK_NOT_FOUND_MSG, id)));

        return mapper.map(task);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@RequestBody TaskCreateDTO data) {
        var task = mapper.map(data);
        taskRepository.save(task);

        return mapper.map(task);
    }

    @PutMapping(path = "/{id}")
    public TaskDTO update(
            @PathVariable long id,
            @Valid @RequestBody TaskUpdateDTO data
    ) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(TASK_NOT_FOUND_MSG, id)));
        var user = userRepository.findById(data.getAssigneeId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, data.getAssigneeId())));
        user.addTask(task);
        userRepository.save(user);
        mapper.update(data, task);
        taskRepository.save(task);

        return mapper.map(task);
    }

    @DeleteMapping(path = "/{id}")
    public void destroy(@PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(TASK_NOT_FOUND_MSG, id)));
        var assignee = task.getAssignee();
        assignee.removeTask(task);
        userRepository.save(assignee);

        taskRepository.deleteById(id);
    }
}
