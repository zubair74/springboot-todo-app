package com.example.demo.controllers;

import com.example.demo.models.Todo;
import com.example.demo.services.TodoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(){
        List<Todo> todos = todoService.getTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping({"/{todoId}"})
    public ResponseEntity<Todo> getTodo(@PathVariable Long todoId){
        return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo){
        Todo todo1 = todoService.insert(todo);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("todo", "/api/v1/todo" + todo1.getId().toString());
        return new ResponseEntity<>(todo, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{todoId}"})
    public ResponseEntity<Todo> updateTodo(@PathVariable("todoId") Long id,@RequestBody Todo todo){
        todoService.updateTodo(id, todo);
        return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/{todoId}"})
    public ResponseEntity<Todo> deleteTodo(@PathVariable("todoId") Long id){
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
