# Eight Queen Problem

The eight queens puzzle is the problem of placing eight chess queens on an 8×8 chessboard so that no two queens threaten each other, thus, a solution requires that no two queens share the same row, column, or diagonal.

given an initial configuration of 8×8 chessboard. The eight queens puzzle has 92 distinct solutions. We have to find any valid final configuration starting from the given initial configuration.

We solve the 8-queens problem using the following algorithms:
1) Hill Climbing Algorithm
2) K-Beam Search Algorithm.
3) Genetic Algorithm.
4) Constraint Satisfaction Problem (CSP).


## Hill Climbing:

The hill climbing algorithm gets its name from the metaphor of climbing a hill, where the peak is h=0. But there is more than one way to climb a hill. If we always choose the path with the best improvement in heuristic cost then we are using the steepest hill variety.

Problem: depending on initial state, can get stuck in local maxima.

## K beam search:

We Keep track of k states rather than just one.

The search begins with k randomly generated states, at each step, all the successors of all k states are generated, if any one of the successors is a goal, the algorithm halts, otherwise, it selects the k best successors from the complete list and repeat.

## Genetic Algorithm: 

Genetic Algorithm is a popular Evolutionary Optimization technique that imitates the process of natural selection. Firstly, in Genetic Algorithm a population of individual chromosomes is selected randomly. These chromosomes each of which has its individual set of characteristics or traits widely known as genotypes. These genotypes can be altered by adopting various strategies including mutation and crossover. These chromosomes are evolved towards better (eventually optimal) solutions for a combinatorial optimization problem. This optimal solution is dependent on the termination criterion. Genetic Algorithm gives the optimal solution depending on the nature of the fitness function and also on the structure of the algorithm used. Here, Genetic Algorithm is used to solve the 8-Queens Problem. 

### A general outline how Genetic Algorithm (GA) works is given below: 
1. A random population of candidate solutions is created and the fitness scores of the individuals are calculated and the chromosomes are sorted in the population and ranked according to the fitness values. 
2. Certain number of chromosomes will pass onto the next generation depending on a selection operator, favoring the better individuals based on their ranking in the population. 
3. Selected chromosomes acting as parents will take part in crossover operation to create children whose fitness values are to be calculated simultaneously. Crossover probability is generally kept high, because it is seen to give better children in terms of fitness values. 
4. Then based on a mutation probability, a mutation operator is applied on new individuals which randomly changes few chromosomes. Mutation probability is generally kept low. 
5. Evaluated off-springs, together with their parents form the population for the next generation.



## Constraint satisfaction problem [CSP]:

Constraint satisfaction problem is based on eliminating the solutions that doesn’t satisfy the conditions on the go.

Each queen has a domain that we choose only from it.
After each choice, we recheck the satisfaction of each node with respect to all other nodes.


## Features

We created a javaFX GUI (graphical user interface) that supports the following
1. Representation to the queens on a real chess grid.
2. The ability to create a problem by Clicking on the wanted blocks from the grid to add or remove queens or Choosing an input file from your computer to open.
3. Choose the wanted algorithm from a drop down list and hit solve to get final grid visualized, cost (Number of steps to reach final board from the initial
board), total run time, number of expanded nodes.
4. Clear the board and restart.
