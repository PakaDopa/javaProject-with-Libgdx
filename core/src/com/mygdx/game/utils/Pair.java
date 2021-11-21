package com.mygdx.game.utils;

public class Pair <K, V>{
    private K key;
    private V value;
    public Pair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
    public K getX() {return key;}
    public V getY() {return value;}
}
