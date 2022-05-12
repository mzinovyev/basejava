package com.urise.webapp.storage;

import java.util.Arrays;
import com.urise.webapp.model.Resume;

public abstract class  AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10_000;
    protected int storageCapacity = 0;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        if (storageCapacity > 0) {
            Arrays.fill(storage, 0, storageCapacity - 1, null);
            storageCapacity = 0;
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

    public final void save(Resume r) {
        if (storageCapacity == storage.length) {
            System.out.println("Error saving resume with UUID " + r + " : storage is full");
            return;
        }

        int index = findIndex(r.toString());
        if (index >= 0) {
            System.out.println("Can't Save. Resume with UUID " + r + " already exist");
            return;
        }

        insertItem(r, index);private Storage storage = new ArrayStorage();
        storageCapacity++;
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("Can't Delete. Resume with UUID " + uuid + " not found");
            return;
        }

        System.arraycopy(storage, index + 1, storage, index, (storageCapacity - 1) - index);
        storage[storageCapacity - 1] = null;
        storageCapacity--;
    }
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }

        System.out.println("Can't Get. Resume with UUID " + uuid + " not found");
        return null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageCapacity);
    }

    public int size() {
        return storageCapacity;
    }

    protected abstract void insertItem(Resume r, int insertIndex);

    protected abstract int findIndex(String uuid);
}
