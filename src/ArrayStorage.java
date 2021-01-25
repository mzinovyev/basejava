/**
 * Array based storage for Resumes
 */

import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int lastUsedIndex = -1;

    void clear() {
        if (lastUsedIndex != -1) {
            Arrays.fill(storage, 0, lastUsedIndex + 1, null);
            lastUsedIndex = -1;
        }
    }

    void save(Resume r) {
        if (lastUsedIndex == storage.length) {
            System.out.println("Error: storage if full");
        } else {
            lastUsedIndex += 1;
            storage[lastUsedIndex] = r;
        }
    }

    Resume get(String uuid) {
        int foundedIndex = search(uuid);
        if (foundedIndex >= 0) return storage[foundedIndex];
        return null;
    }

    void delete(String uuid) {
        int foundedIndex = search(uuid);
        if (foundedIndex >= 0) {
            System.out.println("foundedIndex: " + foundedIndex);
            storage[foundedIndex] = storage[lastUsedIndex];
            storage[lastUsedIndex] = null;
            lastUsedIndex -= 1;
            System.out.println("lastUsedIndex: " + lastUsedIndex);
        } else {
            System.out.println("Cant find uuid 2 delete resume");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (lastUsedIndex >= 0) return Arrays.copyOf(storage, lastUsedIndex + 1);
        else return new  Resume[0];
    }

    int size() {
        return lastUsedIndex + 1;
    }

    // If element with uuid founded? return index. Else, return -1;
    private int search(String uuid) {
        for (int i = 0; i <= lastUsedIndex; i++) {
            if (storage[i].uuid == uuid) {
                return i;
            }
        }
        return -1;
    }
}