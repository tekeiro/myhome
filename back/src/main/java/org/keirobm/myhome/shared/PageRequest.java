package org.keirobm.myhome.shared;

import org.springframework.data.domain.Pageable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PageRequest {
    private int page;
    private int total;

    public Pageable toPageable() {
        return Pageable.ofSize(total).withPage(page);
    }
}
