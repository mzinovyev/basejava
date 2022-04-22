package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    // MEDIT How it works without @Override
    public int size() {
        return lastUsedIndex + 1;
    }

    public void clear() {
        if (lastUsedIndex != -1) {
            Arrays.fill(storage, 0, lastUsedIndex + 1, null);
            lastUsedIndex = -1;
        }
    }

    @Override
    public void delete(String uuid) { /// MEDIT
        Resume r = new Resume(uuid);
        int foundedIndex = search(r);
        if (foundedIndex >= 0) {
            // CHeck System array copy manual
            if (lastUsedIndex - 1 - foundedIndex >= 0)
                System.arraycopy(storage, foundedIndex + 1, storage, foundedIndex, lastUsedIndex - 1 - foundedIndex);
            storage[lastUsedIndex] = null;
            lastUsedIndex--;
        } else {
            System.out.println("Can't Delete. Resume with UUID " + uuid + " not found");
        }
    }

    public void update(Resume r) {
        int foundedIndex = search(r);
        if (foundedIndex >= 0) {
            storage[foundedIndex] = r;
            System.out.println("Updated. Resume with UUID " + r.toString());
        } else {
            System.out.println("Can't Update. Resume with UUID " + r.toString() + " not found");
        }
    }

    // why it can be @override or not ???
    // insertItem didn`t check array size, it checked in save method
    protected void insertItem(Resume r, int insertIndex) {
        int i;
        for (i = lastUsedIndex; (i >=0 && (storage[i].compareTo(r) > 0)); i--)
            storage[i + 1] = storage[i];
        storage[i + 1] = r;
        lastUsedIndex += 1; // increment array capacity after insert
    }

    protected int search(Resume r) {
        return Arrays.binarySearch(storage, 0, lastUsedIndex, r);
    };
}
