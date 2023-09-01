package com.example.todoapp.Database;

import com.example.todoapp.Model.Collections;

import java.util.Comparator;

public class ListComparator implements Comparator<Collections> {

    @Override
    public int compare(Collections t1, Collections t2) {
        return Integer.compare((int)t1.getId(),(int)t2.getId() );
    }
}
