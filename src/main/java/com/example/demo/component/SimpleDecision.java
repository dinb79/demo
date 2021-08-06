package com.example.demo.component;

import com.example.demo.data.model.Sentence;
import com.example.demo.error.CustomException;
import org.springframework.stereotype.Component;

@Component
public class SimpleDecision implements IDecisionAlgorithm {
    @Override
    public boolean isGood(Sentence sentence) throws CustomException {
        return sentence.getSentence().contains("good");
    }

    @Override
    public boolean isFake(Sentence sentence) throws CustomException {
        return sentence.getSentence().contains("Bill Gates");
    }
}
