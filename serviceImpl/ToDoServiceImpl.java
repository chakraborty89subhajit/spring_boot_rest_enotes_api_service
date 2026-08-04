package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.dto.ToDoDTO;
import com.example.enotes_api_service.entity.ToDo;
import com.example.enotes_api_service.enums.TodoStatus;
import com.example.enotes_api_service.exception.ResourceNotFoundException;
import com.example.enotes_api_service.repo.ToDoRepository;
import com.example.enotes_api_service.service.ToDoService;
import com.example.enotes_api_service.util.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoRepository todoRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Validation validation;

    @Override
    public Boolean saveToDo(ToDoDTO todoDTo) throws Exception{
        //validate the inputs
        validation.todoValidation(todoDTo);


        //map todoDTO with ToDo.class
        ToDo todo = mapper.map(todoDTo,ToDo.class);
        //save the todo using todoRepo.save() method
        ToDo saveToDo = todoRepo.save(todo);

        //check if saveToDo saved properly or not
        if(!ObjectUtils.isEmpty(saveToDo)){
            return true;

        }else{
            return false;
        }


    }

    @Override
    public ToDoDTO getToDOById(Integer id) throws Exception {
        ToDo todo = todoRepo.findById(id)
                            .orElseThrow(()->
                               new ResourceNotFoundException
                                ("todo with given id not present"));
           // map todo with ToDoDTO.class
        ToDoDTO todoDTO = mapper.map(todo,ToDoDTO.class);

        setStatus(todoDTO,todo);
        return todoDTO;
    }


    private void setStatus(ToDoDTO toDoDTO, ToDo todo){
        for(TodoStatus st: TodoStatus.values()){
            ToDoDTO.StatusDTO statusDTO = ToDoDTO.StatusDTO.builder()
                    .id(st.getId())
                    .name(st.getName())
                    .build();
            toDoDTO.setStatus(statusDTO);

        }

    }

    @Override
    public List<ToDoDTO> getToDoByUser() {
        Integer userId= 2;
        List<ToDo> todos = todoRepo.findByCreatedBy(userId);
        return todos
                .stream()
                .map(td->mapper.map(td,ToDoDTO.class))
                .collect(Collectors.toList());

    }
}
