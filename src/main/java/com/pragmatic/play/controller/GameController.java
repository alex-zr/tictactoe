package com.pragmatic.play.controller;

import com.pragmatic.play.enums.Piece;
import com.pragmatic.play.persistence.domain.Game;
import com.pragmatic.play.persistence.domain.dto.GameDTO;
import com.pragmatic.play.service.GameService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Log
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final HttpSession httpSession;

    @Autowired
    public GameController(GameService gameService, HttpSession httpSession) {
        this.gameService = gameService;
        this.httpSession = httpSession;
    }

    @GetMapping(value = "/create/{name}")
    public Game createNewGame(@PathVariable String name) {

        GameDTO gameDTO = new GameDTO(1, name, Piece.X);
        Game game = gameService.createNewGame(gameDTO);
        httpSession.setAttribute("gameId", game.getId());

        log.info("new game id: " + httpSession.getAttribute("gameId") + " stored in session");

        return game;
    }

    @GetMapping(value = "/player/list")
    public List<Game> getPlayerGames() {
        return gameService.getGames();
    }

    @GetMapping(value = "/{id}")
    public Game getGameProperties(@PathVariable Long id) {

        httpSession.setAttribute("gameId", id);

        return gameService.getGame(id);
    }
}