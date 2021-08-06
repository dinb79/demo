package com.example.demo.service;

import com.example.demo.data.model.Sentence;
import com.example.demo.repository.SentenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SentenceService implements ISentenceService {
    private static final Logger logger = LoggerFactory.getLogger(SentenceService.class);

    @Autowired
    private SentenceRepository repository;

    @Override
    public Sentence save(Sentence sentence) {
        logger.debug("[SentenceService.save] Saving...");
        return repository.save(sentence);
    }

    @Override
    public Sentence get(String id) {
        logger.debug("[SentenceService.save] Getting sentence from DB...");
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Sentence with id '%s' not found", id)));
    }

    @Override
    public List<Sentence> getAll() {
        logger.debug("[SentenceService.save] Getting all sentences from DB...");
        return new ArrayList<>((Collection<? extends Sentence>) repository.findAll());
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
