package com.example.territorytreemanager;

import com.example.territorytreemanager.service.TerritoryService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TerritoryServiceImplTest {

    @Test
    void testFindAncestors() {
        // Create a mock TerritoryService
        TerritoryService territoryService = mock(TerritoryService.class);

        // Mock behavior for findAncestors method to return ancestors of "Bangalore"
        when(territoryService.findAncestors("Bangalore")).thenReturn(Arrays.asList("India","Karnataka"));

        // Mock behavior for findAncestors method to return ancestors of "Trivandrum"
        when(territoryService.findAncestors("Trivandrum")).thenReturn(Arrays.asList("India", "Kerala"));

        List<String> ancestors = territoryService.findAncestors("Trivandrum");

        // Create an ArrayList for the expected result
        List<String> expectedAncestors = Arrays.asList("India", "Kerala");

        // Assert the result
        assertEquals(expectedAncestors, ancestors);

        // Verify that the findAncestors method was called with the correct argument
        verify(territoryService).findAncestors("Trivandrum");
    }


}
