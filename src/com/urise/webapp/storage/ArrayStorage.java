package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int lastUsedIndex = -1;

    public void clear() {
        if (lastUsedIndex != -1) {
            Arrays.fill(storage, 0, lastUsedIndex + 1, null);
            lastUsedIndex = -1;
        }
    }

    public void save(Resume resume) {
        if (lastUsedIndex == storage.length) {
            System.out.println("Error saving resume with UUID " + resume.toString() + " : storage is full");
        } else {
            int foundedIndex = search(resume.toString());
            if (foundedIndex < 0) {
                lastUsedIndex++;
                storage[lastUsedIndex] = resume;
            } else {
                System.out.println("Can't Save. Resume with UUID " + resume.toString() + " already exist");
            }

        }
    }

    public void update(Resume resume) {
        int foundedIndex = search(resume.toString());
        if (foundedIndex >= 0) {
            storage[foundedIndex] = resume;
            System.out.println("Updated. Resume with UUID " + resume.toString());
        } else {
            System.out.println("Can't Update. Resume with UUID " + resume.toString() + " not found");
        }
    }

    public Resume get(String uuid) {
        int foundedIndex = search(uuid);
        if (foundedIndex >= 0) return storage[foundedIndex];
        System.out.println("Can't Get. Resume with UUID " + uuid + " not found");
        return null;
    }

    public void delete(String uuid) {
        int foundedIndex = search(uuid);
        if (foundedIndex >= 0) {
            storage[foundedIndex] = storage[lastUsedIndex];
            storage[lastUsedIndex] = null;
            lastUsedIndex--;
        } else {
            System.out.println("Can't Delete. Resume with UUID " + uuid + " not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, lastUsedIndex + 1);
    }

    public int size() {
        return lastUsedIndex + 1;
    }

    // If element with uuid founded, return index. Else, return -1;
    private int search(String uuid) {
        for (int i = 0; i <= lastUsedIndex; i++) {
            if (storage[i].toString() == uuid) {
                return i;
            }
        }
        return -1;
    }
}