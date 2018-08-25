package ua.com.danit.service;

import ua.com.danit.enums.GameStatus;
import ua.com.danit.persistence.domain.Game;
import ua.com.danit.persistence.domain.Position;
import ua.com.danit.persistence.domain.dto.GameDTO;

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
