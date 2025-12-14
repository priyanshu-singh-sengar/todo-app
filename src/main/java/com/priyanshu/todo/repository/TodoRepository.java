package com.priyanshu.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priyanshu.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
