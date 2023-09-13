package ru.merrymary.service;

import ru.merrymary.dto.TextDto;
import ru.merrymary.dto.TextStatDto;

public interface TextService {
    TextStatDto analyse(TextDto textDto, String ip);
}
