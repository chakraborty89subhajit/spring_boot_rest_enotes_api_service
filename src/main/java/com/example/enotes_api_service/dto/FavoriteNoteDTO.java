package com.example.enotes_api_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteNoteDTO {
    private Integer id;
    private NotesDTO note;
    private Integer userId;

}
