package com.example.ecollector.model;

public class ItemModel {

    private Long id;
    private String name;
    private String description;
    private double value;

    public ItemModel(Long id,String name, String description, double value)
    {
        this.id=id;
        this.name = name;
        this.description= description;
        this.value=value;
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

    public void setValue(double value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return name+"    wartość: "+value+" zł" ;
    }
}
