package ru.merrymary.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TextStatDto {
    private final int symbolsQty;
    private final int symbolsNotSpacesQty;
    private final int wordsQty;
    private final String charsFreq;
}
