package com.example.ecollector.model;

import java.util.ArrayList;
import java.util.List;

public class ItemModel {

    private Long id;
    private String name;
    private String description;
    private Long value;
    private List<CollectionModel> collections;

    public ItemModel(Long id,String name, String description, Long value)
    {
        this.id=id;
        this.name = name;
        this.description= description;
        this.value=value;
        this.collections = new ArrayList<>();
    }
    public void setCollections(List<CollectionModel> collections) {
        this.collections = collections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nazwa) {
        this.name = nazwa;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }
    public List<CollectionModel> getCollections() { return collections; }

    public void setValue(Long value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return name+"    wartość: "+value+" zł" ;
    }
}
