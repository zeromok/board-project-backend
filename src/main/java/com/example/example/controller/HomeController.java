package com.example.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
public class HomeController {

    @GetMapping("/")
    public String Init() {
        log.info("Page Init");

        LocalDateTime date  = LocalDateTime.now();
        return date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
    }
} // end
