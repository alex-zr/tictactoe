package com.pragmatic.play.service;

import com.pragmatic.play.enums.GameStatus;
import com.pragmatic.play.enums.Piece;
import com.pragmatic.play.persistence.domain.Game;
import com.pragmatic.play.persistence.domain.Move;
import com.pragmatic.play.persistence.domain.Position;
import com.pragmatic.play.persistence.domain.dto.CreateMoveDTO;
import com.pragmatic.play.persistence.domain.dto.MoveDTO;

import java.util.List;

public interface MoveService {
    Move createMove(Game game, Piece piece, CreateMoveDTO createMoveDTO);

    GameStatus checkCurrentGameStatus(Game game, Piece piece);

    List<MoveDTO> getMovesInGame(Game game);

    List<Position> getTakenMovePositionsInGame(Game game);

    List<Position> getMovePositionsForPeace(Game game, Piece piece);
}
