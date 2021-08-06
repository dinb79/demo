package com.example.demo.component;

import com.example.demo.data.model.Sentence;
import com.example.demo.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BatchDecisionMaker extends AbstractDecisionMaker {
    public static final String DECISION_TYPE_SIMPLE = "SIMPLE";
    public static final String DECISION_TYPE_AI = "AI";

    @Autowired
    private IDecisionAlgorithm decisionAlgorithm;

    @Override
    protected boolean isGood(Sentence sentence) throws CustomException {
        return decisionAlgorithm.isGood(sentence);
    }

}
