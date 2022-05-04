package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertItem(Resume r, int foundedIndex) {
        storage[lastUsedIndex + 1] = r;
        lastUsedIndex += 1; // increment array capacity after insert
    }

    // If element with uuid founded, return index. Else, return -1;
    protected int search(String uuid) {
        for (int i = 0; i <= lastUsedIndex; i++) {
            if (storage[i].toString() == uuid) {
                return i;
            }
        }
        return -1;
    }
}