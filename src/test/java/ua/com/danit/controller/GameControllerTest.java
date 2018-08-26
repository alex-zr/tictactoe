package ua.com.danit.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.danit.config.SecurityConfig;
import ua.com.danit.persistence.domain.Game;
import ua.com.danit.service.GameService;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
@Import(SecurityConfig.class)
public class GameControllerTest {

    @MockBean
    private GameService gameService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPlayerGames() throws Exception {
        final String name = "name";
        when(gameService.getGames()).thenReturn(Arrays.asList(Game.builder()
                .name(name)
                .build()));
        mockMvc.perform(get("/game/player/list")
                //.content(MediaType.APPLICATION_JSON))
                .with(httpBasic("test", "test")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(name)));
    }
}