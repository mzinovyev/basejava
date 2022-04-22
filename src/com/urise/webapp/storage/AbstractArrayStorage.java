package com.urise.webapp.storage;

import java.util.Arrays;
import com.urise.webapp.model.Resume;

public abstract class  AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10_000;
    protected int lastUsedIndex = -1;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        if (lastUsedIndex != -1) {
            Arrays.fill(storage, 0, lastUsedIndex + 1, null);
            lastUsedIndex = -1;
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

    public void save(Resume r) {
        if (lastUsedIndex == storage.length) {
            System.out.println("Error saving resume with UUID " + r.toString() + " : storage is full");
            return;
        }

        int foundedIndex;
        if(lastUsedIndex < 0) { //check if array is empty
            foundedIndex = 0;
        } else {
            foundedIndex = search(r);
            if (!(foundedIndex < 0)) {
                System.out.println("Can't Save. Resume with UUID " + r.toString() + " already exist");
                return;
            }
        }

        insertItem(r, foundedIndex);
    }

    public Resume get(String uuid) {
        Resume r = new Resume(uuid);
        int foundedIndex = search(r);
        if (search(r) >= 0) {
            return storage[foundedIndex];
        } else {
            System.out.println("Can't Get. Resume with UUID " + uuid + " not found");
            return null;
        }
    }

    public int size() {
        return lastUsedIndex + 1;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, lastUsedIndex + 1);
    }

    protected abstract void insertItem(Resume r, int foundedIndex);

    protected abstract int search(Resume r);
}
