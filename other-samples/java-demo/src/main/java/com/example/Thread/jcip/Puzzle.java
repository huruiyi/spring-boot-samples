package com.example.Thread.jcip;

import java.util.Set;

/**
 * Puzzle
 * <p/>
 * Abstraction for puzzles like the 'sliding blocks puzzle'
 *
 * @author Brian Goetz and Tim Peierls
 */
public interface Puzzle<P, M> {

  P initialPosition();

  boolean isGoal(P position);

  Set<M> legalMoves(P position);

  P move(P position, M move);
}
