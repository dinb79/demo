package com.example.demo.service;

import com.example.demo.data.model.Sentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class FakeSentenceService implements ISentenceService {
    private static final Logger logger = LoggerFactory.getLogger(FakeSentenceService.class);
    private static final String DEFAULT_SENTENCE_PREFIX = "This is a fake sentence with the word ";
    private static final int MAX_SENTENCES = 5;

    @Override
    public Sentence save(Sentence sentence) {
        logger.debug("[FakeSentenceService.save] Saving...");
        return sentence;
    }

    @Override
    public Sentence get(String id) {
        logger.debug("[FakeSentenceService.get] Getting fake sentence...");
        Sentence s = generate();
        s.setId(id);
        return s;
    }

    @Override
    public List<Sentence> getAll() {
        logger.debug("[FakeSentenceService.save] Getting fake sentences...");
        List<Sentence> sentences = new ArrayList<>();
        for(int i=0; i<MAX_SENTENCES; i++) {
            sentences.add(generate());
        }
        return sentences;
    }

    @Override
    public void deleteAll() {
        // do nothing
    }

    private Sentence generate() {
        Sentence s = new Sentence();
        s.setId(UUID.randomUUID().toString());
        s.setSentence(DEFAULT_SENTENCE_PREFIX + (new Random().nextBoolean() ? "good" : "bad"));
        return s;
    }
}
