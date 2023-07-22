package com.example.Spring.demoK;

public interface SequenceDao {

  Sequence getSequence(String sequenceId);

  int getNextValue(String sequenceId);
} 
