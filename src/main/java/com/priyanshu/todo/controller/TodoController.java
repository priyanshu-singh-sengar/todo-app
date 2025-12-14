package com.priyanshu.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.priyanshu.todo.service.TodoService;

@Controller
public class TodoController {

    private final TodoService todoService;

    // Constructor injection
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Show all todos
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        return "index";
    }

    // Add new todo
    @PostMapping("/add")
    public String addTodo(@RequestParam String title) {
        todoService.createTodo(title);
        return "redirect:/";
    }

    // Toggle todo completion
    @PostMapping("/toggle/{id}")
    public String toggleTodo(@PathVariable Long id) {
        todoService.toggleTodo(id);
        return "redirect:/";
    }

    // Delete todo
    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }

    // Edit todo
    @PostMapping("/edit/{id}")
    public String editTodo(@PathVariable Long id,
          @RequestParam String title) {
        todoService.updateTodo(id, title);
        return "redirect:/";
    }
}
