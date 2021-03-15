# PALab2
## The Transportation Problem
This repository contains all the problems proposed for the second laboratory in Advanced Programming course solved by me.

# Essentials tools
You need to have Java RE or JDK >= 8 installed on your computer.


# Build and run the above programs
Launch IntelliJ IDEA and click :arrow_forward: in the gutter and select Run 'Main()' in the popup. The IDE starts compiling your code.
When the compilation is complete, the Run tool window opens at the bottom of the screen


# Tasks
An instance of the Transportation Problem consists of source and destinations.
Each source has a given capacity, i.e. how many units of a commodity it is able to supply to the destinations.
Each destination demands a certain amount of commodities.
The cost of transporting a unit of commodity from each source to each destination is given by a cost matrix (or function).
We consider the problem of determining the quantities to be transported from sources to destinations, in order to minimize the total transportation cost. The supply and demand constraints must be satisfied. (We may assume that all the values are integer).

Consider the following example.
``` 
        D1     D2     D3     Supply
S1      2      3      1      10  
S2      5      4      8      35  
S3      5      6      8      25  
Demand  20     25     25     
```
A solution may be something like that:

```
S1 -> D3: 10 units * cost 1 = 10
S2 -> D2: 25 units * cost 4 = 100
S2 -> D3: 10*8 = 80
S3 -> D1: 20*5 = 100
S3 -> D3: 5*8 = 40
Total cost: 330
```

# Compulsory
- [x] Create an object-oriented model of the problem. You should have (at least) the following classes: Source, Destination, Problem.
- [x] The sources and the destinations have names. The sources will also have the property type. The available types will be implemented as an enum . For example:
```
public enum SourceType {
    WAREHOUSE, FACTORY;
}
```
- [x] Assume S1 is a factory and S2, S3 are warehouses.
- [x] Each class should have appropriate constructors, getters and setters.
- [x] Use the IDE features for code generation, such as generating getters and setters.
- [x] The toString method form the Object class must be properly overridden for all the classes.
- [x] Use the IDE features for code generation, for example (in NetBeans) press Alt+Ins or invoke the context menu, select "Insert Code" and then "toString()" (or simply start typing "toString" and then press Ctrl+Space).
- [x] Create and print on the screen the instance of the problem described in the example.
## Output
``` 
        D1     D2     D3     Supply
S1      2      3      1      10  
S2      5      4      8      35  
S3      5      6      8      25  
Demand  20     25     25     
Process finished with exit code 0
```
# Optional
- [x] Override the equals method form the Object class for the Source, Destination classes. The problem should not allow adding the same source or destination twice.
- [x] Instead of using an enum, create dedicated classes for warehouses and factories. Source will become abstract.
- [x] Create a class to describe the solution.
- [x] Implement a simple algorithm for creating a feasible solution to the problem (one that satisfies the supply and demand constraints).
- [x] Write doc comments in your source code and generate the class documentation using javadoc.
## Output
```
        D1     D2     D3     Supply
S1      2      3      1      10  
S2      5      4      8      35  
S3      5      6      8      25  
Demand  20     25     25   
S1 --> D3: 10 units * 1 cost = 10
S2 --> D2: 25 units * 4 cost = 100
S2 --> D1: 10 units * 5 cost = 50
S3 --> D1: 10 units * 5 cost = 50
S3 --> D3: 15 units * 8 cost = 120
Total cost: 330
```
# Bonus
- [x] Implement an algorithm in order to minimize the total cost, using either: an heuristic, for example the Vogel's approximation method and an exact algorithm based on minimum cost network flows.
- [x] Generate large random instances and analyze the performance of your algorithm (running times, memory consumption). Identify the hot-spots in your code.
## Output
```
Enter a number.
5
        D0     D1     D2     D3     D4     Supply
S0      7      6      9      6      2      58  
S1      8      3      8      2      7      36  
S2      2      7      6      2      5      35  
S3      8      2      9      4      6      15  
S4      8      7      6      9      7      25  
Demand  21     22     40     49     37     
37 units * 2 cost = 74
15 units * 2 cost = 30
7 units * 3 cost = 21
21 units * 2 cost = 42
25 units * 6 cost = 150
14 units * 2 cost = 28
29 units * 2 cost = 58
6 units * 6 cost = 36
15 units * 9 cost = 135
Total cost: 574
1510529000 nanoseconds.
```






