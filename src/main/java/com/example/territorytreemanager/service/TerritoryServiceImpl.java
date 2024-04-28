package com.example.territorytreemanager.service;

import com.example.territorytreemanager.model.TerritoriesData;
import com.example.territorytreemanager.model.Territory;
import com.example.territorytreemanager.model.TerritoryNode;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class TerritoryServiceImpl implements TerritoryService {

    private final ResourceLoader resourceLoader;
    @Autowired
    public TerritoryServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<Territory> readJSON() {
        try {
            InputStream inputStream = resourceLoader.getResource("classpath:territories.json").getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            TerritoriesData territoriesData = objectMapper.readValue(inputStream, TerritoriesData.class);
            return territoriesData != null ? territoriesData.getTerritories() : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON file: territories.json", e);
        }
    }

    @Override
    public TerritoryNode convertToTree(List<Territory> territories) {
        Map<Long, TerritoryNode> nodeMap = new HashMap<>();
        TerritoryNode rootNode = null;

        for (Territory territory : territories) {
            TerritoryNode node = new TerritoryNode();
            node.setTerritory(territory);
            node.setChildren(new ArrayList<>());
            nodeMap.put(territory.getId(), node);
            if (territory.getParentId() == 0) {
                rootNode = node;
            }
        }

        for (Territory territory : territories) {
            if (territory.getParentId() != 0) {
                TerritoryNode parentNode = nodeMap.get(territory.getParentId());
                TerritoryNode childNode = nodeMap.get(territory.getId());
                if (parentNode != null && childNode != null) {
                    parentNode.getChildren().add(childNode);
                    childNode.setParent(parentNode);
                } else {

                    System.err.println("Error: Missing parent or child node for territory ID: " + territory.getId());
                }
            }
        }
        return rootNode;
    }

    private TerritoryNode findNodeByName(String name, TerritoryNode node) {
        if (node.getTerritory().getName().equals(name)) {
            return node;
        }
        for (TerritoryNode child : node.getChildren()) {
            TerritoryNode foundNode = findNodeByName(name, child);
            if (foundNode != null) {
                return foundNode;
            }
        }
        return null;
    }

    private void addAncestors(TerritoryNode node, List<String> ancestors) {
        if (node != null) {
            ancestors.add(0, node.getTerritory().getName());
            addAncestors(node.getParent(), ancestors);
        }
    }

    @Override
    public List<String> findAncestors(String territoryName) {
        List<Territory> territories = readJSON();
        TerritoryNode rootNode = convertToTree(territories);
        if (rootNode != null) {
            TerritoryNode territoryNode = findNodeByName(territoryName, rootNode);
            if (territoryNode != null) {
                List<String> ancestors = new ArrayList<>();
                addAncestors(territoryNode.getParent(), ancestors);
                return ancestors;
            }
        }
        return new ArrayList<>();
    }

}
