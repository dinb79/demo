package com.example.demo.component;

import com.example.demo.data.model.Sentence;
import com.example.demo.error.CustomException;
import com.example.demo.service.ISentenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AbstractDecisionMaker {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDecisionMaker.class);

    @Autowired
    private ISentenceService sentenceService;

    @Value("${decisionMaker.maxAcceptedExceptions:0}")
    private int maxAcceptedExceptions;

    public final boolean run() {
        int nExceptions = 0;
        boolean result = false;
        int nGood = 0;

        try {
            logger.debug("[run] START");
            logger.debug(String.format("[run] maxAcceptedExceptions = %d", maxAcceptedExceptions));
            List<Sentence> sentences = sentenceService.getAll();
            for(Sentence sentence : sentences) {
                try {
                    if(isGood(sentence)) {
                        sentence.setJudgement(true);
                        sentenceService.save(sentence);
                        nGood++;
                    }
                } catch(CustomException ce) {
                    if(++nExceptions == maxAcceptedExceptions) {
                        throw new Exception(String.format("Too many CustomException: (%d)", nExceptions));
                    } else {
                        logger.error("[run] Caught a CustomException, skipping", ce);
                        continue;
                    }
                }
            }
            result = (nGood == sentences.size());
        } catch(Exception e) {
            logger.error("[run] Unexpected exception, aborting", e);
            result = false;
        } finally {
            logger.debug("[run] END");
        }
        return result;
    }

    // leave the implementation details to the
    protected abstract boolean isGood(Sentence sentence) throws CustomException;
}
