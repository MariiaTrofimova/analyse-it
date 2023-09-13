package ru.merrymary.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.merrymary.dto.TextDto;
import ru.merrymary.dto.TextStatDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class TextServiceTest {

    private String ip;

    private final TextService service;

    @BeforeEach
    void setup() {
        ip = "1.1.1.1";
    }

    @Test
    void shouldAnalyseSingleChar() {
        TextDto textDto = new TextDto("a");
        TextStatDto textStatDto = service.analyse(textDto, ip);
        assertNotNull(textStatDto);
        assertEquals(1, textStatDto.getSymbolsQty());
        assertEquals(1, textStatDto.getSymbolsNotSpacesQty());
        assertEquals(1, textStatDto.getWordsQty());
        assertEquals('a', textStatDto.getCharsFreq().charAt(0));
    }

    @Test
    void shouldAnalyseCharWithSpaceNotInMiddle() {
        TextDto textDto = new TextDto("a ");
        TextStatDto textStatDto = service.analyse(textDto, ip);
        assertNotNull(textStatDto);
        assertEquals(1, textStatDto.getSymbolsQty());
        assertEquals(1, textStatDto.getSymbolsNotSpacesQty());
        assertEquals(1, textStatDto.getWordsQty());
        assertEquals('a', textStatDto.getCharsFreq().charAt(0));
    }

    @Test
    void shouldAnalyseCharWithSpace() {
        TextDto textDto = new TextDto("a a");
        TextStatDto textStatDto = service.analyse(textDto, ip);
        assertNotNull(textStatDto);
        assertEquals(3, textStatDto.getSymbolsQty());
        assertEquals(2, textStatDto.getSymbolsNotSpacesQty());
        assertEquals(2, textStatDto.getWordsQty());
        assertEquals('a', textStatDto.getCharsFreq().charAt(0));
    }

    @Test
    void shouldAnalyseRegularCase() {
        TextDto textDto = new TextDto("aa b");
        TextStatDto textStatDto = service.analyse(textDto, ip);
        assertNotNull(textStatDto);
        assertEquals(4, textStatDto.getSymbolsQty());
        assertEquals(3, textStatDto.getSymbolsNotSpacesQty());
        assertEquals(2, textStatDto.getWordsQty());
        assertEquals('a', textStatDto.getCharsFreq().charAt(0));
    }
}