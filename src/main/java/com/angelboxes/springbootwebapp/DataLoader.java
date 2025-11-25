package com.angelboxes.springbootwebapp;

import com.angelboxes.springbootwebapp.model.Todo;
import com.angelboxes.springbootwebapp.model.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    private final TodoRepository todoRepository;

    public DataLoader(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String... args) {
        todoRepository.save(new Todo(100001, "Angel", "Learn Spring Boot", new Date(), false));
        todoRepository.save(new Todo(100002, "Angel", "Learn Angular", new Date(), false));
        todoRepository.save(new Todo(100003, "Angel", "Learn Kafka", new Date(), false));
        todoRepository.save(new Todo(100004, "Angel", "Learn to live", new Date(), false));
    }
}
