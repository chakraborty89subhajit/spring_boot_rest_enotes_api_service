package com.example.enotes_api_service.util;

import com.example.enotes_api_service.handler.GenericResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonUtil {

    public static ResponseEntity<?> createBuildResponse(Object data, HttpStatus status){
        GenericResponse response = GenericResponse.builder()
                .responseStatus(status)
                .status("success")
                .message("success")
                .data(data)
                .build();
        return response.create();


    }

    public static ResponseEntity<?> createBuildResponseMessage(
            String message,HttpStatus status
    ){

        GenericResponse response = GenericResponse.builder()
                .responseStatus(status)
                .status("success")
                .message(message)
                .build();
        return response.create();
    }

    //create error response
    public static ResponseEntity<?> createErrorResponse(
            Object data, HttpStatus status
    ){
        GenericResponse response = GenericResponse.builder()
                .responseStatus(status)
                .status("failed")
                .message("failed")
                .data(data)
                .build();
        return response.create();
    }

    //create error response message

    public static ResponseEntity<?> createErrorResponseMessage(
            String message,HttpStatus status
    ){
        GenericResponse response = GenericResponse.builder()
                .responseStatus(status)
                .status("failed")
                .message(message)
                .build();
        return response.create();

    }

    public static String getContentType(String originalFileName) {

        if (originalFileName == null) {
            return "application/octet-stream";
        }

        String extension = FilenameUtils
                .getExtension(originalFileName)
                .toLowerCase();

        switch (extension) {

            case "pdf":
                return "application/pdf";

            case "jpg":
            case "jpeg":
                return "image/jpeg";

            case "png":
                return "image/png";

            case "txt":
                return "text/plain";

            case "xlsx":
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

            case "docx":
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

            default:
                return "application/octet-stream";
        }
    }

}
