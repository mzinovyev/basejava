package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertItem(Resume r, int foundedIndex) {
        storage[lastUsedIndex + 1] = r;
        lastUsedIndex += 1; // increment array capacity after insert
    }

//    public void delete(String uuid) {
//        Resume r = new Resume(uuid);
//        int foundedIndex = search(r);
//        if (foundedIndex >= 0) {
//            storage[foundedIndex] = storage[lastUsedIndex];
//            storage[lastUsedIndex] = null;
//            lastUsedIndex--;
//        } else {
//            System.out.println("Can't Delete. Resume with UUID " + uuid + " not found");
//        }
//    }

    // If element with uuid founded, return index. Else, return -1;
    protected int search(Resume r) {
        for (int i = 0; i <= lastUsedIndex; i++) {
            if (storage[i].compareTo(r) == 0) {
                return i;
            }
        }
        return -1;
    }
}