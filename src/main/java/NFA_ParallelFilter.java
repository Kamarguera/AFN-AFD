import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Java main class that filters the standard input stream using Buffered reader
 * to read the ".txt" file. Words that are accepted by the non-deterministic
 * finite state automata echo the accepted words.
 * <p>
 * In contrast, words that are rejected are echoed as rejected word in the NFA.
 *
 * @author Mohammed Waliur Rahman
 * @Coursework Finite State Automata
 * @id 17002953
 * @module Formal Specification & Software Implementation
 * @modulecode CS6001
 * @moduleleader Dr. Vassil Vassilev
 */

public class NFA_ParallelFilter {
    public static void main(String[] args) throws IOException { // If ".txt" file fails to be read

        // instance of the NFA parallel search class
        NFA_Parallel_Search machine = new NFA_Parallel_Search();

        BufferedReader INP = // Standard Input
                new BufferedReader(new InputStreamReader(System.in));

        /* Read and echo line by line until End Of File (EOF) */
        String s = INP.readLine();
        while (s != null) {
            machine.restart(); // Begin from start state {E} = 1<<0 in bitwise
            machine.transition(s); // Read per alphabet of each word in the ".txt"

            /* Prints words that correspond to being in final state {E} - 1<<5  */
            if (machine.accepted()) System.out.println("Accepted: " + s + "\n");

            /* Only if the NFA machine reads a word that doesn't end in final state {E} - 1<<5 */
            if (machine.rejected()) System.out.println("Rejected: " + s + "\n");

            s = INP.readLine();
        }
    }
}

