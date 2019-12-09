package com.example.ecollector.model;

public class CollectionItemModel {
    private long idItem;
    private long idCollection;

    public CollectionItemModel(long idStudent, long idGroup){
        this.idItem = idStudent;
        this.idCollection= idGroup;
    }

    public long getIdStudent(){ return this.idItem; }
    public long getIdGroup(){ return this.idCollection; }
}
