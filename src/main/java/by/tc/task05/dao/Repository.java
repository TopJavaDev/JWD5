package by.tc.task05.dao;

import java.util.List;
import java.util.Map;

public class Repository <T> {
    private final static Repository repository = new Repository();
    private Map<Integer, List<T>> storedData;
    private int maxPages;

    private Repository() {}

    public static Repository getRepository() {
        return repository;
    }

    public void setStoredData(Map<Integer, List<T>> storedData) {
        this.storedData = storedData;
    }

    public Map<Integer, List<T>> getStoredData() {
        return storedData;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }
}
