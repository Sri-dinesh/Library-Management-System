#!/bin/bash

# Define directories
SRC_DIR="src/main/java"
OUT_DIR="out"
MAIN_CLASS="com.library.LibraryManagementSystem"

# Clean up previous builds
echo "Cleaning up..."
rm -rf "$OUT_DIR"
mkdir -p "$OUT_DIR"

# Compile the project
echo "Compiling modularized Library Management System..."
javac -d "$OUT_DIR" -sourcepath "$SRC_DIR" "$SRC_DIR/com/library/LibraryManagementSystem.java"

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Starting application..."
    echo "--------------------------------------------------"
    # Run the application
    java -cp "$OUT_DIR" "$MAIN_CLASS"
else
    echo "Compilation failed. Please check the errors above."
    exit 1
fi