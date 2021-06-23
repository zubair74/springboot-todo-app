package com.example.demo.services;

import com.example.demo.models.Todo;
import com.example.demo.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getTodos() {
        List todos = new ArrayList();
        todoRepository.findAll().forEach(todos::add);
        return todos;
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).get();
    }

    @Override
    public Todo insert(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void updateTodo(Long id, Todo todo) {
        Todo todoFromDB = todoRepository.findById(id).get();
        System.out.println(todoFromDB.toString());
        todoFromDB.setTitle(todo.getTitle());
        todoFromDB.setDescription(todo.getDescription());
        todoFromDB.setTodoStatus(todo.getTodoStatus());
        todoRepository.save(todoFromDB);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
