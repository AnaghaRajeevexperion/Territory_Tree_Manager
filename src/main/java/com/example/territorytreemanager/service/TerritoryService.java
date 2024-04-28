package com.example.territorytreemanager.service;

import com.example.territorytreemanager.model.Territory;
import com.example.territorytreemanager.model.TerritoryNode;

import java.util.List;

public interface TerritoryService {
    List<Territory> readJSON();
    TerritoryNode convertToTree(List<Territory> territories);
    List<String> findAncestors(String territoryName);
}

