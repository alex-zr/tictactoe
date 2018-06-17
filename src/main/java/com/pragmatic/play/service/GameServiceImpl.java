package com.pragmatic.play.service;

import com.pragmatic.play.enums.GameStatus;
import com.pragmatic.play.persistence.domain.Game;
import com.pragmatic.play.persistence.domain.Position;
import com.pragmatic.play.persistence.domain.dto.GameDTO;
import com.pragmatic.play.persistence.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createNewGame(GameDTO gameDTO) {
        Game game = new Game();

        game.setName(gameDTO.getName());
        game.setGameStatus(GameStatus.IN_PROGRESS);

        game.setCreated(new Date());
        gameRepository.save(game);

        return game;
    }

    @Override
    public Game updateGameStatus(Game game, GameStatus gameStatus) {
        Game resGame = getGame(game.getId());
        resGame.setGameStatus(gameStatus);

        return resGame;
    }

    @Override
    public boolean isWinner(List<Position> positions) {
        return getWinningPositions().stream()
                .anyMatch(positions::containsAll);
    }

    @Override
    public List<List<Position>> getWinningPositions() {
        List<List<Position>> winingPositions = new ArrayList<>();

        winingPositions.add(asList(new Position(1, 1), new Position(1, 2), new Position(1, 3)));
        winingPositions.add(asList(new Position(2, 1), new Position(2, 2), new Position(2, 3)));
        winingPositions.add(asList(new Position(3, 1), new Position(3, 2), new Position(3, 3)));

        winingPositions.add(asList(new Position(1, 1), new Position(2, 1), new Position(3, 1)));
        winingPositions.add(asList(new Position(1, 2), new Position(2, 2), new Position(3, 2)));
        winingPositions.add(asList(new Position(1, 3), new Position(2, 3), new Position(3, 3)));

        winingPositions.add(asList(new Position(1, 1), new Position(2, 2), new Position(3, 3)));
        winingPositions.add(asList(new Position(3, 1), new Position(2, 2), new Position(1, 3)));

        return winingPositions;
    }

    @Override
    public boolean isBoardFull(List<Position> takenPositions) {
        return takenPositions.size() == 9;
    }

    @Override
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game getGame(Long id) {
        return gameRepository.findById(id).get();
    }
}
