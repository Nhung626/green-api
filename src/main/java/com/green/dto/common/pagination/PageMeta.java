package com.green.dto.common.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PageMeta implements Serializable {
    @JsonProperty("page")
    int page;
    @JsonProperty("size")
    int size;
    @JsonProperty("total_elements")
    long totalElements;
    @JsonProperty("total_pages")
    int totalPages;

    public PageMeta(int page, int size, long totalElements, int totalPages) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    @JsonProperty("page")
    public void setPage(final int page) {
        this.page = page;
    }

    @JsonProperty("size")
    public void setSize(final int size) {
        this.size = size;
    }

    @JsonProperty("total_elements")
    public void setTotalElements(final long totalElements) {
        this.totalElements = totalElements;
    }

    @JsonProperty("total_pages")
    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return this.page;
    }

    public int getSize() {
        return this.size;
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public String toString() {
        return "PageMeta(page=" + this.getPage() + ", size=" + this.getSize() + ", totalElements=" + this.getTotalElements() + ", totalPages=" + this.getTotalPages() + ")";
    }
}

