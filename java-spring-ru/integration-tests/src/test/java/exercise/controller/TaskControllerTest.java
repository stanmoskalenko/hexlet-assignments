package exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import exercise.model.Task;
import exercise.repository.TaskRepository;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// END
@SpringBootTest
@AutoConfigureMockMvc
// BEGIN
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Faker faker;
    @Autowired
    private TaskRepository taskRepository;


    @BeforeEach
    public void prepareRepository() {
        taskRepository.deleteAll();
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getUpdatedAt))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph(1))
                .create();
        taskRepository.save(task);
    }

    private Task getTestTask() {
        return taskRepository.findAll().stream().findFirst().orElse(null);
    }

    private final Map<String, String> testNewTaskData = Map.of(
            "title", "testTitle",
            "description", "testDescription");

    private ObjectMapper getMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper;
    }

    @Test
    @DisplayName("test index controller")
    void testIndex() throws Exception {
        var mapper = getMapper();
        var actual = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();
        var responseBody = actual.getResponse().getContentAsString();
        var expected = List.of(mapper.convertValue(getTestTask(), Map.class));

        assertThatJson(responseBody).isArray().containsAll(expected);
    }

    @Test
    @DisplayName("test show controller")
    void testShow() throws Exception {
        var mapper = getMapper();
        var actualFound = mockMvc.perform(get("/tasks/" + getTestTask().getId()))
                .andExpect(status().isOk())
                .andReturn();
        var responseBody = actualFound.getResponse().getContentAsString();
        var expected = mapper.convertValue(getTestTask(), Map.class);

        assertThatJson(responseBody).isObject()
                .containsAllEntriesOf(expected);
        var actualNotFound = mockMvc.perform(get("/tasks/9999"))
                .andExpect(status().isNotFound())
                .andReturn();
        var responseBodyNeg = actualNotFound.getResponse().getContentAsString();

        assertThat(responseBodyNeg).isEqualTo("Task with id 9999 not found");
    }

    @Test
    @DisplayName("test create controller")
    void testCreate() throws Exception {
        var mapper = getMapper();
        var request = post("/tasks")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(testNewTaskData))
                .contentType(MediaType.APPLICATION_JSON);

        var actual = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        var savedTask = taskRepository.findByTitle("testTitle").orElseThrow();
        var responseBody = actual.getResponse().getContentAsString();
        var expected = mapper.convertValue(savedTask, Map.class);

        assertThatJson(responseBody).isObject()
                .containsAllEntriesOf(expected);
    }

    @Test
    @DisplayName("test update controller")
    void testUpdate() throws Exception {
        var mapper = getMapper();
        var request = put("/tasks/" + getTestTask().getId())
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(testNewTaskData))
                .contentType(MediaType.APPLICATION_JSON);
        var actualFound = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        var responseBody = actualFound.getResponse().getContentAsString();
        var expected = mapper.convertValue(getTestTask(), Map.class);
        assertThatJson(responseBody).isObject()
                .containsAllEntriesOf(expected);

        var requestNotFound = put("/tasks/9999")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(testNewTaskData))
                .contentType(MediaType.APPLICATION_JSON);
        var actualNotFound = mockMvc.perform(requestNotFound)
                .andExpect(status().isNotFound())
                .andReturn();
        var responseBodyNeg = actualNotFound.getResponse().getContentAsString();
        assertThat(responseBodyNeg).isEqualTo("Task with id 9999 not found");
    }

    @Test
    @DisplayName("test delete controller")
    void testDelete() throws Exception {
        var taskId = getTestTask().getId();
        var request = delete("/tasks/" + taskId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        var actual = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        assertThat(actual.getResponse().getContentAsString()).isEmpty();
        assertThat(taskRepository.findById(taskId)).isNotPresent();
    }
}
// END