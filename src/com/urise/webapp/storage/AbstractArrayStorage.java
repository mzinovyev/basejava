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
        int index = findIndex(r.toString());
        if (index >= 0) {
            storage[index] = r;
            System.out.println("Updated. Resume with UUID " + r);
        } else {
            System.out.println("Can't Update. Resume with UUID " + r + " not found");
        }
    }

    public void save(Resume r) {
        if (lastUsedIndex == storage.length) {
            System.out.println("Error saving resume with UUID " + r.toString() + " : storage is full");
            return;
        }

        if(lastUsedIndex < 0) { //check if array is empty
            storage[0] = r;
            lastUsedIndex = 0;
            return;
        }

        int index = findIndex(r.toString());
        if (index >= 0) {
            System.out.println("Can't Save. Resume with UUID " + r + " already exist");
            return;
        }

        insertItem(r, - (index + 1));
        lastUsedIndex ++;// increment array capacity after insert
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Can't Delete. Resume with UUID " + uuid + " not found");
            return;
        }

        if (index == lastUsedIndex) { // delete last element
            storage[index] = null;
            lastUsedIndex -= 1;
            return;
        }

        deleteItem(index);
    }
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println("Can't Get. Resume with UUID " + uuid + " not found");
            return null;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, lastUsedIndex + 1);
    }

    public int size() {
        return lastUsedIndex + 1;
    }

    protected abstract void insertItem(Resume r, int index);

    protected void deleteItem(int index){
        System.arraycopy(storage, index + 1, storage, index, lastUsedIndex - index);
        storage[lastUsedIndex] = null;
        lastUsedIndex--;
    }

    protected abstract int findIndex(String uuid);
}
