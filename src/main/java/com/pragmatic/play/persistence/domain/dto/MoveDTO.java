package com.pragmatic.play.persistence.domain.dto;

import com.pragmatic.play.enums.GameStatus;
import com.pragmatic.play.enums.Piece;
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
