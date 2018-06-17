package com.pragmatic.play.service;

import com.pragmatic.play.persistence.domain.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceImplTest {

    @Autowired
    private GameService gameService;

    @Test
    public void isWinnerVertical() {
        List<Position> verticalPositions = Arrays.asList(
                new Position(1, 1),
                new Position(2, 1),
                new Position(3, 1)
        );
        assertTrue(gameService.isWinner(verticalPositions));
    }

    @Test
    public void isWinnerDiagonal() {
        List<Position> horizontalPositions = Arrays.asList(
                new Position(1, 1),
                new Position(2, 1),
                new Position(3, 1)
        );
        assertTrue(gameService.isWinner(horizontalPositions));
    }

    @Test
    public void isWinnerHorizontal() {
        List<Position> verticalPositions = Arrays.asList(
                new Position(1, 1),
                new Position(1, 2),
                new Position(1, 3)
        );
        assertTrue(gameService.isWinner(verticalPositions));
    }

    @Test
    public void isWinnerNo() {
        List<Position> verticalPositions = Arrays.asList(
                new Position(1, 1),
                new Position(1, 2),
                new Position(3, 3)
        );
        assertFalse(gameService.isWinner(verticalPositions));
    }

    @Test
    public void isWinnerSingle() {
        List<Position> singlePosition = Arrays.asList(
                new Position(1, 1)
        );
        assertFalse(gameService.isWinner(singlePosition));
    }

    @Test
    public void isWinnerEmpty() {
        assertFalse(gameService.isWinner(new ArrayList<>()));
    }

    @Test
    public void isBoardFull() {
        List<Position> fullBoardPositions = Arrays.asList(
                new Position(1, 1),
                new Position(1, 2),
                new Position(1, 3),
                new Position(2, 1),
                new Position(2, 2),
                new Position(2, 3),
                new Position(3, 1),
                new Position(3, 2),
                new Position(3, 3)
        );

        assertTrue(gameService.isBoardFull(fullBoardPositions));
    }

    @Test
    public void isBoardFullPartial() {
        List<Position> fullBoardPositions = Arrays.asList(
                new Position(1, 1),
                new Position(1, 2),
                new Position(1, 3),
                new Position(2, 1),
                new Position(2, 2),
                new Position(2, 3),
                new Position(3, 1),
                new Position(3, 3)
        );
        assertFalse(gameService.isBoardFull(fullBoardPositions));
    }

    @Test
    public void isBoardFullSingle() {
        List<Position> singlePosition = Arrays.asList(
                new Position(3, 3)
        );
        assertFalse(gameService.isBoardFull(singlePosition));
    }

    @Test
    public void isBoardFullEmpty() {
        assertFalse(gameService.isBoardFull(new ArrayList<>()));
    }
}