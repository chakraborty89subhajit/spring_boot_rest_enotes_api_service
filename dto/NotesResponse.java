package com.example.enotes_api_service.dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotesResponse {
    private List<NotesDTO> notes;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isFirst;
    private Boolean isLast;

}
