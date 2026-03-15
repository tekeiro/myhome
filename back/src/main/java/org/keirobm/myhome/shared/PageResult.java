package org.keirobm.myhome.shared;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PageResult<T> {
    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private int pageSize;

    private List<T> content;

    public static <T> PageResult<T> of(List<T> content, Page pageInfo) {
        return PageResult.<T>builder()
            .totalPages(pageInfo.getTotalPages())
            .totalElements(pageInfo.getTotalElements())
            .pageNumber(pageInfo.getNumber())
            .pageSize(pageInfo.getSize())
            .content(content)
            .build();
    }
}
