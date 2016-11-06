package com.alcuvi.algorithms.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


/**
 * Subset client. Write a client program Subset.java that takes a command-line integer k; 
 * reads in a sequence of N strings from standard input using StdIn.readString(); 
 * and prints out exactly k of them, uniformly at random. Each item from the sequence 
 * can be printed out at most once. You may assume that 0 ≤ k ≤ n, where N is the number 
 * of string on standard input.
 * @author alcuvi
 *
 */

public class Subset {

    public static void main(String[] args) {
    	
        int k = new Integer(args[0]);
        
        final RandomizedQueue<String> rndQueue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String element = StdIn.readString();
            rndQueue.enqueue(element);
        }

        while (k > 0) {
            StdOut.println(rndQueue.dequeue());
            k--;
        }
    }
}

// Testing methods in Subset
// *-----------------------------------------------------------
// Tests 1-3 call the main() function directly, resetting standard input
// before each call.
//
// Running 3 total tests.
//
// Test 1: assignment inputs
//
// % echo "A B C D E F G H I" | java Subset 3
// [student solution]
// H
// F
// G
//
// % echo "A B C D E F G H I" | java Subset 3
// [student solution]
// F
// I
// E
//
// % echo "AA BB BB BB BB BB CC CC " | java Subset 8
// [student solution]
// BB
// BB
// AA
// CC
// BB
// BB
// CC
// BB
//
// ==> passed
//
// Test 2: various inputs
//
// % echo "A B C D E F G H I" | java Subset 1
// [student solution]
// F
//
// % echo "A B C D E F G H I" | java Subset 5
// [student solution]
// G
// D
// F
// H
// B
//
// % echo "A B C D E F G H I" | java Subset 5
// [student solution]
// F
// D
// A
// C
// G
//
// % echo "A B C D E F G H I" | java Subset 9
// [student solution]
// C
// B
// F
// A
// G
// E
// H
// I
// D
//
// % echo "A B C D E F G H I" | java Subset 0
// [student solution]
//
// % echo "it was the best of times it was the worst of times" | java Subset 10
// [student solution]
// was
// times
// it
// of
// was
// it
// the
// worst
// times
// of
//
// % echo "It was the best of times, it was the worst of times, it was ..." |
// java Subset 10
// [student solution]
// with
// had
// to
// the
// prison."
// had
// it
// and
// manner
// young
//
// % echo "It was the best of times, it was the worst of times, it was ..." |
// java Subset 20
// [student solution]
// zealous
// an
// Why
// secret
// any
// He
// from
// a
// an
// of
// ending
// only
// of
// of
// restore
// But
// much
// brought
// and
// any
//
// % echo "AA BB BB BB BB BB CC CC " | java Subset 7
// [student solution]
// AA
// BB
// CC
// BB
// BB
// BB
// BB
//
// ==> passed
//
// Test 3: check that subsets are uniformly random
// * 1000 subsets of size 1 from subset10.txt
// * 250 subsets of size 4 from subset10.txt
// * 600 subsets of size 1 from subset6.txt
// * 300 subsets of size 2 from subset6.txt
// * 800 subsets of size 1 from subset8.txt
// * 160 subsets of size 5 from subset8.txt
// * 1000 subsets of size 1 from mediumTale.txt
// ==> passed
//
// Total: 3/3 tests passed!
//
