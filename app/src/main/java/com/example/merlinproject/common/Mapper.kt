package com.example.merlinproject.common

interface Mapper<F, T> {
    fun mapFrom(from: F): T
}