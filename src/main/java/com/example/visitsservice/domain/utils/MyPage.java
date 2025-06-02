package com.example.visitsservice.domain.utils;

import java.util.List;

public class MyPage<T>{
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean orderAsc;
    private long totalObjects;

    public MyPage(List<T> content, int page, int size, boolean orderAsc, long totalObjects) {
        this.page = page;
        this.size = size;
        this.orderAsc = orderAsc;
        this.totalElements = content.size();
        this.totalPages = (int) Math.ceil((double) totalObjects / size);
        this.content = content;
        this.totalObjects = totalObjects;

    }

    public List<T> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isOrderAsc() {
        return orderAsc;
    }

    public long getTotalObjects() {
        return totalObjects;
    }
}
