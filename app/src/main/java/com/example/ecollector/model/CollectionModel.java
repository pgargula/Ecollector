package com.example.ecollector.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CollectionModel {

    private Long id;
    private String name;
    private List<ItemModel> items;

    public CollectionModel(Long id,String name)
    {
        this.id=id;
        this.name = name;
        this.items= new ArrayList<>();
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

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return name ;
    }
}
