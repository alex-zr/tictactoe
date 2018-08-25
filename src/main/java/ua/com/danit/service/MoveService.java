package ua.com.danit.service;

import ua.com.danit.enums.GameStatus;
import ua.com.danit.enums.Piece;
import ua.com.danit.persistence.domain.Game;
import ua.com.danit.persistence.domain.Move;
import ua.com.danit.persistence.domain.Position;
import ua.com.danit.persistence.domain.dto.CreateMoveDTO;
import ua.com.danit.persistence.domain.dto.MoveDTO;

import java.util.List;

public interface MoveService {
    Move createMove(Game game, Piece piece, CreateMoveDTO createMoveDTO);

    GameStatus checkCurrentGameStatus(Game game, Piece piece);

    List<MoveDTO> getMovesInGame(Game game);

    List<Position> getTakenMovePositionsInGame(Game game);

    List<Position> getMovePositionsForPeace(Game game, Piece piece);
}
