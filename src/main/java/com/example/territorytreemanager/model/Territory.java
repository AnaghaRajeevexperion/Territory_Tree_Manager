package com.example.territorytreemanager.model;

import lombok.Data;
@Data
public class Territory {
    private long id;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String name;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    private long parentId;
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
}
