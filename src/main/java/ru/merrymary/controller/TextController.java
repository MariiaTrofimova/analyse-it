package ru.merrymary.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.merrymary.dto.TextDto;
import ru.merrymary.dto.TextStatDto;
import ru.merrymary.service.TextService;

@Controller
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class TextController {
    private final TextService service;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String analyse(@Valid @ModelAttribute TextDto textDto, HttpServletRequest request, Model model) {
        String ip = request.getRemoteAddr();
        log.info("Request to analyse text of length: {} was sent from ip {}",textDto.getText().length(), ip);
        TextStatDto textStat = service.analyse(textDto, ip);
        model.addAttribute("yourText", textDto.getText());
        model.addAttribute("charFreq", textStat.getCharsFreq());
        model.addAttribute("wordsQty", textStat.getWordsQty());
        model.addAttribute("charsQty", textStat.getSymbolsQty());
        model.addAttribute("nCharsQty", textStat.getSymbolsNotSpacesQty());
        return "result";
    }
}