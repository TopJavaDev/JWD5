package by.tc.task05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paginator<T> {
    private int maxDisplayedEntities;
    private int maxPages;
    private List<T> initialEntities;
    private Map<Integer, List<T>> resultEntities = new HashMap<>();

    public Paginator(int displayMaxValue, List<T> initialEntities) {
        this.maxDisplayedEntities = displayMaxValue;
        this.initialEntities = initialEntities;
        initializePagination();
    }

    private void initializePagination() {
        int entityQuantity = initialEntities.size();
        int position = 0;
        int entitiesToAppend = entityQuantity % maxDisplayedEntities;
        maxPages = entityQuantity / maxDisplayedEntities;

        for (int i = 0; i < maxPages; i++) {
            List<T> dividedEntities = new ArrayList<>();
            for (int j = 0; j < maxDisplayedEntities; j++) {
                dividedEntities.add(j, initialEntities.get(position + j));
            }
            position += maxDisplayedEntities;
            resultEntities.put(i, dividedEntities);
        }
        if (entitiesToAppend != 0) {
            List<T> additionalEntities = new ArrayList<>();
            for (int j = 0; j < entitiesToAppend; j++) {
                additionalEntities.add(j, initialEntities.get(position + j));
            }
            resultEntities.put(maxPages++, additionalEntities);
        }
    }

    public int getMaxPages() {
        return maxPages;
    }

    public Map<Integer, List<T>> getResultEntities() {
        return resultEntities;
    }
}
