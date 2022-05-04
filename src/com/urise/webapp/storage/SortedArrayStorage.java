package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    // why it can be @override or not ???
    // insertItem didn`t check array size, it checked in save method
    protected void insertItem(Resume r, int insertIndex) {
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, storageCapacity - insertIndex);
        storage[insertIndex] = r;
        storageCapacity ++; // increment array capacity after insert
    }

    protected int findIndex(String uuid) {
        Resume r = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, storageCapacity, r);
    };
}
