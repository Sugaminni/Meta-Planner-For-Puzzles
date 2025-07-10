# Intelligent Puzzle Solver

An AI puzzle solver that decides whether to use BFS, A*, or UCS for solving puzzles like the N-Puzzle.

---

## Overview

- Defines puzzles via a common `Puzzle` interface.
- Implements:
  - **BFS** solver
  - **A\*** solver (with Manhattan heuristic)
  - **UCS** solver
- Includes a **MetaPlanner** that:
  - Analyzes the puzzle
  - Chooses the best solver automatically

---

## Supported Puzzle

- **8-Puzzle (sliding tiles)**
  - Goal: arrange tiles in order with blank space at the end.
