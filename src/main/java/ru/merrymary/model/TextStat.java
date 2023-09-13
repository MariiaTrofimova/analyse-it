package ru.merrymary.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class TextStat {
    private final int symbolsQty;
    private final int symbolsNotSpacesQty;
    private final int wordsQty;
    private final Map<Character, Integer> charsFreq;
}
