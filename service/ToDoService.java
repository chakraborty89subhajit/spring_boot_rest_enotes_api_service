package com.example.enotes_api_service.service;

import com.example.enotes_api_service.dto.ToDoDTO;

import java.util.List;

public interface ToDoService {
    public Boolean saveToDo(ToDoDTO todo) throws Exception;
    public ToDoDTO getToDOById(Integer id) throws Exception;
    public List<ToDoDTO> getToDoByUser();
}
