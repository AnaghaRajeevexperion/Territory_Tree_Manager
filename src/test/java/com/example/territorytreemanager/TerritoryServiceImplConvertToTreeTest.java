package com.example.territorytreemanager;

import com.example.territorytreemanager.model.Territory;
import com.example.territorytreemanager.model.TerritoryNode;
import com.example.territorytreemanager.service.TerritoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNull;

class TerritoryServiceImplConvertToTreeTest {

    private TerritoryServiceImpl territoryService;

    @BeforeEach
    void setUp() {
        territoryService = new TerritoryServiceImpl(null);
    }
    @Test
    void testConvertToTree_WithNoTerritories_ReturnsNull() {

        List<Territory> territories = new ArrayList<>();

        TerritoryNode rootNode = territoryService.convertToTree(territories);

        assertNull(rootNode);
    }

}
