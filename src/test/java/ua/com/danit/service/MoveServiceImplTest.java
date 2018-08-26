package ua.com.danit.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.danit.enums.GameStatus;
import ua.com.danit.persistence.domain.Game;
import ua.com.danit.persistence.domain.Move;
import ua.com.danit.persistence.domain.dto.MoveDTO;
import ua.com.danit.persistence.repository.GameRepository;
import ua.com.danit.persistence.repository.MoveRepository;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoveServiceImplTest {

    @Mock
    private GameRepository gameRepository;
    @Mock
    private MoveRepository moveRepository;

    @InjectMocks
    private MoveServiceImpl moveService;

    @Test
    public void getMovesInGame() {
        Game game = Game.builder()
                .id(1L)
                .name("game")
                .gameStatus(GameStatus.IN_PROGRESS)
                .created(new Date())
                .build();

        Move move = new Move();
        move.setGame(game);
        List<Move> moves = Arrays.asList(move);
        MoveDTO moveDTO = new MoveDTO();
        moveDTO.setGameStatus(GameStatus.IN_PROGRESS);
        List<MoveDTO> moveDtos = Arrays.asList(moveDTO);

        when(moveRepository.findByGame(game)).thenReturn(moves);

        assertEquals(moveDtos, moveService.getMovesInGame(game));
    }
}