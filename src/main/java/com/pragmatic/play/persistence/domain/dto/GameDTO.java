package com.pragmatic.play.persistence.domain.dto;

import com.pragmatic.play.enums.Piece;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameDTO {
    int id;
    String name;
    Piece piece;
}


