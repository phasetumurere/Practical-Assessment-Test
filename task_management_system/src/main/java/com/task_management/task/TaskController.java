package com.task_management.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TaskController {
    @Autowired private TaskService service;
    
    @GetMapping("/tasks")
    public String showUserList(Model model) {
        List<Task> listTasks = service.listAll();
        model.addAttribute("listTasks", listTasks);

        return "testing";
    }

    @GetMapping("/tasks/new")
    public String showNewForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("pageTitle", "Add New Task");
        return "testing";
    }

    @PostMapping("/tasks/save")
    public String saveUser(Task task, RedirectAttributes ra) {
        service.save(task);
        ra.addFlashAttribute("message", "The task has been saved successfully.");
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Task task = service.get(id);
            model.addAttribute("task", task);
            model.addAttribute("pageTitle", "Edit Task (ID: " + id + ")");

            return "testing";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/tasks";
        }
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The task ID " + id + " has been deleted.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/tasks";
    }
}
