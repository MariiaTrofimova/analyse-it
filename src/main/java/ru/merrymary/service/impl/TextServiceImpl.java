package ru.merrymary.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.merrymary.dto.TextDto;
import ru.merrymary.dto.TextStatDto;
import ru.merrymary.mapper.TextStatMapper;
import ru.merrymary.model.TextStat;
import ru.merrymary.service.TextService;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TextServiceImpl implements TextService {
    @Override
    public TextStatDto analyse(TextDto textDto, String ip) {
        String s = textDto.getText()
                .strip()
                .replace("\n", "")
                .replace("\r", "");
        return TextStatMapper.toTextStatDto(countStats(s));
    }

    private TextStat countStats(String s) {
        int[] qty = new int[3];
        Map<Character, Integer> charFreq = new HashMap<>();

        s.chars().forEach(ch -> {
            qty[0]++;
            if (ch != ' ') {
                qty[1]++;
                charFreq.put((char) ch, charFreq.getOrDefault((char) ch, 0) + 1);
            } else {
                qty[2]++;
            }
        });
        return new TextStat(qty[0], qty[1], qty[2] + 1, charFreq);
    }
}