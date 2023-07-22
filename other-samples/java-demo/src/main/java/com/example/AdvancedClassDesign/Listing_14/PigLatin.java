package com.example.AdvancedClassDesign.Listing_14;

interface SuffixFunction {

  void call();
}

class PigLatin {

  public static void main(String[] args) {
    String word = "hello";
    SuffixFunction suffixFunc = () -> System.out.println(word + "ay");
    suffixFunc.call();
  }
}
