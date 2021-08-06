package com.example.demo.repository;

import com.example.demo.data.model.Sentence;
import org.springframework.data.repository.CrudRepository;

public interface SentenceRepository extends CrudRepository<Sentence, String> {
}
