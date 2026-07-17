package com.example.enotes_api_service.enums;

public enum TodoStatus {

    NOT_STARTED(1,"not started"),
    IN_PROGRESS(2,"in progress"),
    COMPLETED(3,"completed");

    private Integer id;
    private String name;

    TodoStatus(Integer id,String name){
     this.id= id;
     this.name=name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;


    }


}
