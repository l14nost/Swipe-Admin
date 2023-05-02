package com.example.Swipe.Admin.service;

import java.util.List;

public interface DaoCRUDService<T>{
    List<T> findAll();
    T findById(int id);
    void saveEntity (T t);
    void deleteById(int id);
    void updateEntity(T t, int id);

}
