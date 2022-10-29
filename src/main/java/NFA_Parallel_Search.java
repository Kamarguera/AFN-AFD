    /**
     * A non-deterministic finite-state automaton that defines a language L of all
     * strings over alphabet {0, 1} that contain two pairs of adjacent 0’s separated
     * by an even number of alphabet symbols.
     *
     * @Coursework Finite State Automata
     * @id 17002953
     * @author Mohammed Waliur Rahman
     * @module Formal Specification & Software Implementation
     * @modulecode CS6001
     * @moduleleader Dr. Vassil Vassilev
     */
    public class NFA_Parallel_Search {

        /*
         * The set of states Q = {A,B,C,D,E,F}, start state s = A, final state F = {E},
         * rest being set of non-final states Q\F = {B,C,D,F}.
         *
         * Encoded in bitwise operations: state i is represented by the bit 1<<i.
         *
         * 1<<0 = {A}, 1<<1 = {B}, 1<<2 = {C}, 1<<3 = {F}, 1<<4 = {D}, 1<<5 = {E}
         */

        private int Q; // Represents set of non-final (Q\F) and Final (F) states

        /**
         * Reset the current state to the start state {A}
         */
        public void restart() {
            Q = 1<<0; // Start state, s = A
        }

        /*
         * The transition relation from one state to another
         * is represented as a 2 dimensional array.
         *
         * Modified according to the transition diagram (Section A - group report)
         * using UML state chart diagram for the non-deterministic finite state automata.
         *
         * The set of next states from a given state s and
         * character c is at delta[s,c-'0'].
         */

        static private int[][] delta =

                {{1<<0|1<<1, 1<<0},     // delta[A,0] = {A,B}
                        // delta[A,1] = {A}

                        {1<<2, 0},             // delta[B,0] = {C}
                        // delta[B,1] = {} - 1 isn't read in the state B.

                        {1<<4|1<<3, 1<<3},     // delta[C,0] = {D,F}
                        // delta[C,1] = {F}

                        {1<<2, 1<<2},          // delta[F,0] = {C}
                        // delta[F,1] = {C}

                        {1<<5, 0},             // delta[D,0] = {E}
                        // delta[D,1] = {} - 1 isn't read in the state D.

                        {1<<5, 1<<5}};         // delta[E,0] = {E}
        // delta[E,1] = {E}

        /**
         * Make one transition from state to state
         * on each char in a given string/word.
         *
         * @param in - in the string to use.
         */
        public void transition(String in) {
            for (int i = 0; i < in.length(); i++) {
                char c = in.charAt(i);
                int nextSS = 0; // next state, initially empty (Start State)
                for (int s = 0; s <= 5; s++) { // for each state s
                    if ((Q & (1 << s)) != 0) { // if maybe in s
                        try {
                            nextSS |= delta[s][c - '0'];
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            // in effect, nextSS |= 0 - no more transitions can be made...
                        }
                    }
                }
                Q = nextSS; // new state after c
            }
        }

        /**
         * Test whether the NFA accept the word/string.
         * @return true if the final set includes
         * an accepting state.
         */
        public boolean accepted() {
            return (Q & (1<<5)) != 0; // true if word in final state {E}
        }

        /**
         * Test whether the NFA rejects the word/string.
         * @return false if the final set includes
         * a rejecting state.
         */
        public boolean rejected() {
            return (Q & (1<<5)) == 0; // false if word not in final state {E}
        }
    }

