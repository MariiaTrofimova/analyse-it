package ru.merrymary.mapper;

import ru.merrymary.dto.TextStatDto;
import ru.merrymary.model.TextStat;

import java.util.*;

public class TextStatMapper {

    public static TextStatDto toTextStatDto(TextStat textStat) {
        return TextStatDto.builder()
                .charsFreq(makeTextList(textStat.getCharsFreq()))
                .symbolsQty(textStat.getSymbolsQty())
                .symbolsNotSpacesQty(textStat.getSymbolsNotSpacesQty())
                .wordsQty(textStat.getWordsQty())
                .build();
    }

    private static String makeTextList(Map<Character, Integer> charFreq) {
        Map<Integer, List<Character>> characters = new HashMap<>();
        charFreq.forEach((k, v) -> characters.computeIfAbsent(v, key -> new ArrayList<>()).add(k));
        StringBuilder charStats = new StringBuilder();
        characters.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(entry -> {
                    entry.getValue().forEach(ch -> charStats.append(ch).append(" "));
                    charStats.append(" ---> ").append(entry.getKey());
                    charStats.append("\n");
                });
        return charStats.toString();
    }
}
