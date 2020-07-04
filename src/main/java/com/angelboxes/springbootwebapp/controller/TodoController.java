package com.angelboxes.springbootwebapp.controller;

import com.angelboxes.springbootwebapp.model.Todo;
import com.angelboxes.springbootwebapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService todoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/list-todos")
    public String showTodos(ModelMap modelMap) {
        String name = (String) modelMap.get("name");
        modelMap.addAttribute("todos", todoService.retrieveTodos(name));
        return "list-todos";
    }

    @GetMapping("/add-todo")
    public String showAddTodos(ModelMap modelMap) {
        modelMap.addAttribute("todo", new Todo(0, (String) modelMap.get("name"), "", new Date(), false));
        return "todo";
    }

    @PostMapping("/add-todo")
    public String addTodos(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        String name = (String) modelMap.get("name");
        todoService.addTodo(name, todo.getDesc(), todo.getTargetDate(), false);
        modelMap.addAttribute("todos", todoService.retrieveTodos(name));
        return "redirect:/list-todos";
    }

    @GetMapping("/delete-todo")
    public String deleteTodo(ModelMap modelMap, @RequestParam int id) {
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @GetMapping("/update-todo")
    public String showUpdateTodo(ModelMap modelMap, @RequestParam int id) {
        Todo todo = todoService.retrieveTodo(id);
        modelMap.addAttribute("todo", todo);
        return "todo";
    }

    @PostMapping("/update-todo")
    public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        String name = (String) modelMap.get("name");
        todo.setUser(name);
        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }

}
