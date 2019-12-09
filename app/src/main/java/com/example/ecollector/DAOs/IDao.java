package com.example.ecollector.DAOs;

import java.util.List;

public interface IDao<T> {
    long save(T type);
    T get(long id);
    List<T> getAll();
}
