package com.task_management.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired private TaskRepository repo;

    public List<Task> listAll() {
        return (List<Task>) repo.findAll();
    }

    public void save(Task task) {
        repo.save(task);
    }

    public Task get(Integer id) throws UserNotFoundException {
        Optional<Task> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any users with ID " + id);
        }
        repo.deleteById(id);
    }
}
