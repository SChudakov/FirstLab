package com.sschudakov.utils;

/**
 * Created by Semen Chudakov on 24.09.2017.
 */
public class Substring {
    private int begin;
    private int end;

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }


    public Substring(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + this.begin + ";" + this.end + ")";
    }
}
