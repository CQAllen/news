package com.allenliu.news.service.es;

import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;
import java.util.List;

public class ElasticSearchResult<T> {
    private long total;
    private List<Hit<T, Void>> data;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<SearchResult.Hit<T, Void>> getData() {
        return data;
    }

    public void setData(List<SearchResult.Hit<T, Void>> data) {
        this.data = data;
    }
}