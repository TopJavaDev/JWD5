package by.tc.task05.dao;

import by.tc.task05.model.Book;

import java.util.List;
import java.util.Map;

public class Repository {
    private final static Repository repository = new Repository();
    private Map<Integer, List<Book>> storedData;
    private int maxPages;

    private Repository() {}

    public static Repository getRepository() {
        return repository;
    }

    public void setStoredData(Map<Integer, List<Book>> storedData) {
        this.storedData = storedData;
    }

    public Map<Integer, List<Book>> getStoredData() {
        return storedData;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }
}
