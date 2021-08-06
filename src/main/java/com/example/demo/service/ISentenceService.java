package com.example.demo.service;

import com.example.demo.data.model.Sentence;

import java.util.List;

public interface ISentenceService {
    Sentence save(Sentence sentence);
    Sentence get(String id);
    List<Sentence> getAll();
    void deleteAll();
}
