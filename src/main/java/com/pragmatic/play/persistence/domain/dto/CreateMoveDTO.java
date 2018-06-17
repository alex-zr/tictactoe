package com.pragmatic.play.persistence.domain.dto;

import lombok.Data;

@Data
public class CreateMoveDTO {
    int boardRow;
    int boardColumn;
    String pieceCode;
}
