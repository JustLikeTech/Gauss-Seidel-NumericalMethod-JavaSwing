# Gauss-Seidel Method with GUI (Java Swing)

This project is a **Java Swing application** that solves a **system of 2 linear equations** using the **Gauss-Seidel Iterative Method**.  
It provides a simple graphical interface for input, initial guesses, tolerance, and maximum iterations, then displays iteration results step by step.

## Features
- GUI-based input for:
  - Coefficients of equations (**a11, a12, b1**, **a21, a22, b2**)
  - Initial guess values (**x1, x2**)
  - Maximum iterations
  - Error tolerance
- Displays:
  - Iteration results in tabular form
  - Final convergence information
- Error handling for invalid inputs

## Method Used
The program implements the **Gauss-Seidel Iterative Method**:

\[
x_1^{(k+1)} = \frac{b_1 - a_{12}x_2^{(k)}}{a_{11}}
\]

\[
x_2^{(k+1)} = \frac{b_2 - a_{21}x_1^{(k+1)}}{a_{22}}
\]

Iteration continues until:
\[
|x_1^{(k+1)} - x_1^{(k)}| < \varepsilon \quad \text{and} \quad |x_2^{(k+1)} - x_2^{(k)}| < \varepsilon
\]
or until the maximum number of iterations is reached.

## How to Run
1. Clone this repository:
   ```bash
   git clone https://github.com/username/repo-name.git
