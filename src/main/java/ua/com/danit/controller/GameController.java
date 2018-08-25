package ua.com.danit.controller;

import ua.com.danit.enums.Piece;
import ua.com.danit.persistence.domain.Game;
import ua.com.danit.persistence.domain.dto.GameDTO;
import ua.com.danit.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Log
@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {

    private final GameService gameService;
    private final HttpSession httpSession;

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