package com.example.idiomatic.gradle.javarca.model;

/**
 * Provide the graphical asset for a given symbol as a byte stream that encodes a PNG image.
 */
public record Asset(char symbol, byte[] image) {}
