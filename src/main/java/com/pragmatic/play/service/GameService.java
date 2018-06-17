package com.pragmatic.play.service;

import com.pragmatic.play.enums.GameStatus;
import com.pragmatic.play.persistence.domain.Game;
import com.pragmatic.play.persistence.domain.Position;
import com.pragmatic.play.persistence.domain.dto.GameDTO;

import java.util.List;

public interface GameService {
    Game createNewGame(GameDTO gameDTO);

    Game updateGameStatus(Game game, GameStatus gameStatus);

    boolean isWinner(List<Position> positions);

    List<List<Position>> getWinningPositions();

    boolean isBoardFull(List<Position> takenPositions);

    List<Game> getGames();

    Game getGame(Long id);
}
