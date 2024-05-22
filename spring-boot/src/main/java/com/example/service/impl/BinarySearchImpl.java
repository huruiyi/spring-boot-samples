package com.example.service.impl;

import com.example.service.algorithm.SortAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchImpl {

  private final SortAlgorithm sortAlgorithm;

  public BinarySearchImpl(SortAlgorithm sortAlgorithm) {
    this.sortAlgorithm = sortAlgorithm;
  }

  public int binarySearch(int[] numbers) {
    int[] sortedNumbers = sortAlgorithm.sort(numbers);
    return sortedNumbers.length;
  }

}
