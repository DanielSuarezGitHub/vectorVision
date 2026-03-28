# VectorVision: A Linear Algebra Workspace

## Project Proposal

**VectorVision** is a Java-based desktop application designed to bridge the gap between abstract matrix theory and practical computation. The application allows users to manage a collection of mathematical objects, such as matrices and vectors, to perform complex operations used in **MATH 221** (Linear Algebra) 

**What will the application do?**
The tool acts as a "Matrix Dashboard." Users can input matrices of any size and perform operations like calculating the Reduced Row Echelon Form, finding determinants, and solving systems of equations ($Ax = b$). In later phases, the application will feature a graphical coordinate plane to visualize these transformations in 2D space.

**Who will use it?**
This tool is intended for  students or any learners who need to verify manual calculations and visualize how matrix operations warp and scale space.

**Why is this project of interest to you?**
I plan to do a  Combined Computer Science and Statistics major, and I often find that the jump from numerical matrices to their visual representation is the most difficult part of the curriculum. I want to build a tool that reinforces my understanding of linear algebra while practicing  OOP in Java. I also have worked with Numpy in the past and want to try implementing some of its underlying functionality.

## User Stories
* As a user, I want to be able to **add** a new matrix or vector to my workspace and specify its dimensions and values.
* As a user, I want to be able to **view a list** of all the matrices and vectors currently stored in my workspace.
* As a user, I want to be able to **remove** a specific matrix or vector from my workspace.
* As a user, I want to select a matrix and view its **Reduced Row Echelon Form (RREF)**, including the sequence of row operations performed.
* As a user, I want to be able to **multiply** a matrix by a vector and have the resulting vector automatically added to my workspace.
* As a user, i went to be able to **save** my entire workspace (all matrices and vectors) to a file, if I so choose.
* As a user, I want to be able to **load** my previously saved workspace from a file so I can resume exactly where I left off, if I so choose.

# Instructions for End User
- You can view the panel that displays the Xs that have already been added to the Y by looking at the blue "Workspace List" on the left side of the window.
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by typing X and Y values into the green panel at the bottom and clicking "Add Vector" to save it to the workspace.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by selecting a Vector or Matrix in the Workspace List and clicking "Delete Selected" to remove it from the workspace.
- You can locate my visual component by looking at the central "Cartesian Plane". Adding vectors will draw them here, and clicking a Matrix in the list will visually warp the grid and transform the vectors.
- You can save the state of my application by clicking the 'X' to close the window, which will trigger a popup asking if you want to save.
- You can reload the state of my application by launching the app, which will immediately trigger a popup asking if you want to load your previous session.

# Phase 4: Task 3

If I had more time to work on this project, I would refactor my Model package by extracting common behaviors from my Vector and Matrix classes into an abstract superclass called LinearComponent or an interface like Transformable. Currently, both Vectors and Matrices share similar fundamental traits: they are both composed of a collection of Fraction objects, they both require specific formatting to print to the console or GUI, and they both implement the Writable interface for JSON serialization.

By pushing these shared fields and methods up into an abstract superclass, I could significantly reduce code duplication and improve cohesion. Furthermore, this would allow me to refactor the Workspace class. Instead of maintaining two separate maps one Map<String, Vector> and one Map<String, Matrix>, the Workspace could maintain a single Map<String, LinearComponent>. This would simplify the logic for adding, deleting, and rendering items.