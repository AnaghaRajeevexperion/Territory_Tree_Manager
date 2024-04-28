package com.example.territorytreemanager.controller;

import com.example.territorytreemanager.service.TerritoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TerritoryController {
    private final TerritoryService territoryService;

    public TerritoryController(TerritoryService territoryService) {
        this.territoryService = territoryService;
    }

    @GetMapping("/ancestors")
    public ResponseEntity<List<String>> getAncestors(@RequestParam("territoryName") String territoryName) {
        List<String> ancestors = territoryService.findAncestors(territoryName);
        return ResponseEntity.ok(ancestors); // Return ancestors list even if empty
    }
}
