package dev.andreyrsy.kitchen.flow.controller;

import dev.andreyrsy.kitchen.flow.service.LotesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lotes")
public class LotesController {
    private final LotesService lotesService;

    public LotesController(LotesService lotesService) {
        this.lotesService = lotesService;
    }

    // Entidades @PostMapping, @GetMapping e @DeleteMapping...
}
