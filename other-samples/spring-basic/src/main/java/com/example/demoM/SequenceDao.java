package com.example.demoM;

public interface SequenceDao {

  public Sequence getSequence(String sequenceId);

  public int getNextValue(String sequenceId);
} 
