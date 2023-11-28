package com.task_management;

import com.task_management.task.Task;
import com.task_management.task.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TaskRepositoryTests {
    @Autowired private TaskRepository repo;

    @Test
    public void testAddNew() {
        Task task = new Task();
        task.setEmail("alex.stevenson@gmail.com");
        task.setPassword("alex123456");
        task.setFirstName("Alex");
        task.setLastName("Stevenson");

        Task savedTask = repo.save(task);

        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Task> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (Task task : users) {
            System.out.println(task);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<Task> optionalUser = repo.findById(userId);
        Task task = optionalUser.get();
        task.setPassword("hello2000");
        repo.save(task);

        Task updatedTask = repo.findById(userId).get();
        Assertions.assertThat(updatedTask.getPassword()).isEqualTo("hello2000");
    }

    @Test
    public void testGet() {
        Integer userId = 2;
        Optional<Task> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<Task> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
