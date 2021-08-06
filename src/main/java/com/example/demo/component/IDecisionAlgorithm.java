package com.example.demo.component;

import com.example.demo.data.model.Sentence;
import com.example.demo.error.CustomException;

public interface IDecisionAlgorithm {
    boolean isGood(Sentence sentence) throws CustomException;
    boolean isFake(Sentence sentence) throws CustomException;
}
