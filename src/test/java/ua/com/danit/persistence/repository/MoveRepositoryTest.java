package ua.com.danit.persistence.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.danit.persistence.domain.Game;
import ua.com.danit.persistence.domain.Move;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MoveRepositoryTest {

    @Autowired
    private MoveRepository moveRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByGame() {
        String name = "name";
        Game game = Game.builder()
                .name(name)
                .build();

        Move move = new Move();
        move.setGame(game);
        move.setCreated(new Date());
        entityManager.persist(game);
        entityManager.persist(move); // надо было сохранять

        // https://github.com/alex-zr/tictactoe
        // Проблема была в том что в базу сохраняли только объект game, но репозиторий
        // работает с объектами move, надо было сохранять их и тестировать их

        List<Move> moves = moveRepository.findAll();
        assertThat(moves).hasSize(1).contains(move);
        assertEquals(game, moveRepository.findByGame(game).get(0).getGame());
    }
}