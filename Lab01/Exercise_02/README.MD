# Instructions to Run the Two Commands

To compile and run the Java program, you can follow these steps:

1. **Compile the Java program:**
    ```sh
    javac -cp ".;libs/*" Program.java
    ```

2. **Run the Java program:**
    ```sh
    java -cp ".;./lib.jar" Program
    ```

Note:
- Ensure that you are in the directory containing the `Program.java` file.
- The `libs` directory and the `lib.jar` file must exist and contain the necessary libraries for your program.
- Use a semicolon `;` to separate paths on Windows. If you are using a different operating system, replace it with a colon `:`.

Good luck!