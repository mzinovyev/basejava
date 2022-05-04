package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    // MEDIT How it works without @Override
    public void clear() {
        if (lastUsedIndex != -1) {
            Arrays.fill(storage, 0, lastUsedIndex + 1, null);
            lastUsedIndex = -1;
        }
    }

    public void update(Resume r) {
        int foundedIndex = findIndex(r.toString());
        if (foundedIndex >= 0) {
            storage[foundedIndex] = r;
            System.out.println("Updated. Resume with UUID " + r.toString());
        } else {
            System.out.println("Can't Update. Resume with UUID " + r.toString() + " not found");
        }
    }

    public int size() {
        return lastUsedIndex + 1;
    }

    // why it can be @override or not ???
    // insertItem didn`t check array size, it checked in save method
    protected void insertItem(Resume r, int insertIndex) {
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, (lastUsedIndex + 1) - insertIndex);
        storage[insertIndex] = r;
        lastUsedIndex += 1; // increment array capacity after insert
    }

    protected int findIndex(String uuid) {
        Resume r = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, lastUsedIndex + 1, r);
    };
}
