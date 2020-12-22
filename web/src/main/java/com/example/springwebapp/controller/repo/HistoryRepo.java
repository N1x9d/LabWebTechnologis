package com.example.springwebapp.controller.repo;

import com.example.springwebapp.model.Autor;
import com.example.springwebapp.model.SellHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepo
        extends CrudRepository<SellHistory, Integer> {
}