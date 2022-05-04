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
        int foundedIndex = search(r.toString());
        if (foundedIndex >= 0) {
            storage[foundedIndex] = r;
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

        int searchResult = search(r.toString());
        if (searchResult >= 0) {
            System.out.println("Can't Save. Resume with UUID " + r + " already exist");
            return;
        }

        insertItem(r, - (searchResult + 1)); //
    }

    public void delete(String uuid) {
        int searchResult = search(uuid);
        if (searchResult < 0) {
            System.out.println("Can't Delete. Resume with UUID " + uuid + " not found");
            return;
        }

        if (searchResult == lastUsedIndex) { // delete last element
            storage[searchResult] = null;
            lastUsedIndex -= 1;
            return;
        }

        deleteItem(searchResult);
    }
    public Resume get(String uuid) {
        int foundedIndex = search(uuid);
        if (foundedIndex >= 0) {
            return storage[foundedIndex];
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

    protected abstract void insertItem(Resume r, int foundedIndex);

    protected void deleteItem(int foundedIndex){
        System.arraycopy(storage, foundedIndex + 1, storage, foundedIndex, lastUsedIndex - foundedIndex);
        storage[lastUsedIndex] = null;
        lastUsedIndex--;
    }

    protected abstract int search(String uuid);
}
