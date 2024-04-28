package com.example.territorytreemanager.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TerritoryNode {
    private Territory territory;
    private TerritoryNode parent;

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }
    private List<TerritoryNode> children;
    public TerritoryNode getParent() {
        return parent;
    }
    public TerritoryNode() {
        this.children = new ArrayList<>();
    }

}
