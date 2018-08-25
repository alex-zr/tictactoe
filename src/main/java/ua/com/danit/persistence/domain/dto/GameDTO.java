package ua.com.danit.persistence.domain.dto;

import ua.com.danit.enums.Piece;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameDTO {
    int id;
    String name;
    Piece piece;
}


