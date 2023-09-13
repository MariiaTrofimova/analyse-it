package ru.merrymary.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.merrymary.dto.TextDto;
import ru.merrymary.dto.TextStatDto;
import ru.merrymary.service.TextService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TextController.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class TextControllerTest {
    private static final String PATH = "/";

    @MockBean
    private TextService service;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mvc);
    }

    @Test
    void index() throws Exception {
        mvc.perform(get(PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().contentType(MediaType.valueOf("text/html;charset=UTF-8")));
    }

    @Test
    void shouldAnalyse() throws Exception {
        TextDto textDto = TextDto.builder()
                .text("a")
                .build();
        TextStatDto textStat = TextStatDto.builder()
                .wordsQty(1)
                .symbolsNotSpacesQty(1)
                .symbolsQty(1)
                .charsFreq("a ---> 1")
                .build();

        when(service.analyse(any(), any())).thenReturn(textStat);
        mvc.perform(post(PATH)
                        .param("text", textDto.getText())
                        .param("action", "save")
                        .flashAttr("textDto", new TextDto()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(view().name("result"))
                .andExpect(model().size(6))
                .andExpect(model().attributeExists("charFreq"));
    }

    @Test
    void shouldFailAnalyse() throws Exception {
        mvc.perform(post(PATH)
                        .param("text", "")
                        .param("action", "save")
                        .flashAttr("textDto", new TextDto()))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("index"));
    }
}