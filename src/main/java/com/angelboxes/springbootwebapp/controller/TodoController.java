package com.angelboxes.springbootwebapp.controller;

import com.angelboxes.springbootwebapp.model.Todo;
import com.angelboxes.springbootwebapp.model.TodoRepository;
import com.angelboxes.springbootwebapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TodoController {

/*    @Autowired
    TodoService todoService;*/

    @Autowired
    TodoRepository todoRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/list-todos")
    public String showTodos(ModelMap modelMap) {
        String name = getLoggedInName(modelMap);
        modelMap.addAttribute("todos", todoRepository.findByUser(name));
//        modelMap.addAttribute("todos", todoService.retrieveTodos(name));
        return "list-todos";
    }

    @GetMapping("/add-todo")
    public String showAddTodos(ModelMap modelMap) {
        modelMap.addAttribute("todo", new Todo(0, getLoggedInName(modelMap), "", new Date(), false));
        return "todo";
    }

    @PostMapping("/add-todo")
    public String addTodos(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        String name = getLoggedInName(modelMap);
        todo.setUser(name);
        todoRepository.save(todo);
//        todoService.addTodo(name, todo.getDesc(), todo.getTargetDate(), false);
//        modelMap.addAttribute("todos", todoRepository.findByUser(name));
        return "redirect:/list-todos";
    }

    private String getLoggedInName(ModelMap modelMap) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    @GetMapping("/delete-todo")
    public String deleteTodo(ModelMap modelMap, @RequestParam int id) {
        todoRepository.deleteById(id);
//        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @GetMapping("/update-todo")
    public String showUpdateTodo(ModelMap modelMap, @RequestParam int id) {
        Todo todo = todoRepository.findById(id).get();
//        Todo todo = todoService.retrieveTodo(id);
        modelMap.addAttribute("todo", todo);
        return "todo";
    }

    @PostMapping("/update-todo")
    public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        String name = getLoggedInName(modelMap);
        todo.setUser(name);
        todoRepository.save(todo);
//        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }

}
