package com.priyanshu.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.priyanshu.todo.model.Todo;
import com.priyanshu.todo.repository.TodoRepository;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    // Constructor Injection (BEST PRACTICE)
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Get all todos
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Create a new todo
    public Todo createTodo(String title) {
        Todo todo = new Todo(title);
        return todoRepository.save(todo);
    }

    // Toggle completed status
    public void toggleTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setCompleted(!todo.isCompleted());
        todoRepository.save(todo);
    }

    // Delete todo
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    // Update todo
    public void updateTodo(Long id, String title) {
    Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found"));
    todo.setTitle(title);
    todoRepository.save(todo);
}

}
