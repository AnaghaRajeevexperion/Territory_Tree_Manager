package com.example.territorytreemanager;

import com.example.territorytreemanager.model.Territory;
import com.example.territorytreemanager.service.TerritoryService;
import com.example.territorytreemanager.service.TerritoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TerritoryServiceImplReadJSONTest {

    @Mock
    private ResourceLoader resourceLoader;

    private TerritoryService territoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        territoryService = new TerritoryServiceImpl(resourceLoader);
    }

    @Test
    void testReadJSON() throws IOException {
        // Mock InputStream with JSON data
        String jsonContent = "{\"territories\": [{\"id\": 1, \"name\": \"India\", \"parentId\": 0}]}";
        InputStream inputStream = new ByteArrayInputStream(jsonContent.getBytes(StandardCharsets.UTF_8));

        // Mock Resource object
        Resource resource = mock(Resource.class);
        when(resource.getInputStream()).thenReturn(inputStream);

        // Mock the behavior of resourceLoader.getResource(anyString())
        when(resourceLoader.getResource(anyString())).thenReturn(resource);

        // Test readJSON method
        List<Territory> territories = territoryService.readJSON();

        // Verify the result
        assertEquals(1, territories.size());
        assertEquals("India", territories.get(0).getName());
        assertEquals(0, territories.get(0).getParentId());
    }
}
