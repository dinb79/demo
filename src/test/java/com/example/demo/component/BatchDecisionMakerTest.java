package com.example.demo.component;

import com.example.demo.data.model.Sentence;
import com.example.demo.service.ISentenceService;
import com.example.demo.service.SentenceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BatchDecisionMakerTest {

    @TestConfiguration
    public static class CustomTestConfiguration {

        @Bean
        public ISentenceService createSentenceService() {
            return new SentenceService();
        }

        @Bean
        public IDecisionAlgorithm createDecisionAlgorithm() {
            return new SimpleDecision();
        }

        @Bean
        public AbstractDecisionMaker createDecisionMaker() {
            return new BatchDecisionMaker();
        }
    }

    private static final int N_SENTENCE = 5;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private ISentenceService sentenceService;

    @Autowired
    private IDecisionAlgorithm decisionAlgorithm;

    @Autowired
    private AbstractDecisionMaker decisionMaker;

    @BeforeEach
    public void setUp() {
        sentenceService.deleteAll();
    }

    @AfterEach
    public void cleanUp() {
        sentenceService.deleteAll();
    }

    @Test
    public void when_simple_and_all_good_then_ok() {
        scenarioAllGood(N_SENTENCE);
        boolean result = decisionMaker.run();

        assertThat(result).isTrue();
        List<Sentence> sentences = sentenceService.getAll();
        assertThat(sentences).isNotNull() //
                .hasSize(N_SENTENCE) //
                .allMatch(s -> s.getJudgement().equals(true));
    }

    private void scenarioAllGood(int amount) {
        for(int i=0; i < amount; i++) {
            sentenceService.save(generateOne(String.format("Sentence good number %d", i), null));
        }
    }

    private Sentence generateOne(String sentence, Boolean judgement) {
        Sentence s = new Sentence();
        s.setJudgement(judgement);
        s.setSentence(sentence);
        return s;
    }
}
