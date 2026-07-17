package com.example.enotes_api_service.controller;

import com.example.enotes_api_service.dto.ToDoDTO;
import com.example.enotes_api_service.service.ToDoService;
import com.example.enotes_api_service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class ToDoController {
    @Autowired
    private ToDoService todoService;

    @PostMapping("/")
    public ResponseEntity<?> saveToDo(@RequestBody ToDoDTO todo) throws Exception{
        Boolean saveToDo = todoService.saveToDo(todo);
        if(saveToDo){
            return CommonUtil.createBuildResponseMessage
                    ("todo saved successfully", HttpStatus.CREATED);
        }else{
            return CommonUtil.createErrorResponseMessage
                    ("todo not saved properly",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> saveToDo (@PathVariable Integer id) throws Exception{
        ToDoDTO todo = todoService.getToDOById(id);
        return CommonUtil.createBuildResponse(todo,HttpStatus.OK);
    }


    @GetMapping("/list")
    public ResponseEntity<?> getAllTodoByUser() throws Exception{
        List<ToDoDTO> todoList  = todoService.getToDoByUser();
        if(CollectionUtils.isEmpty(todoList)){
            return ResponseEntity.noContent().build();
        }
        return CommonUtil.createBuildResponse(todoList,HttpStatus.OK);
    }
}
