package com.example.enotes_api_service.controller;

import com.example.enotes_api_service.dto.ToDoDTO;
import com.example.enotes_api_service.service.ToDoService;
import com.example.enotes_api_service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
