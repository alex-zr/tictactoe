package ua.com.danit.persistence.domain.dto;

import ua.com.danit.enums.GameStatus;
import ua.com.danit.enums.Piece;
import lombok.Data;

import java.util.Date;

@Data
public class MoveDTO {
    int boardColumn;
    int boardRow;
    Date created;
    String userName;
    GameStatus gameStatus;
    Piece pieceCode;
}
