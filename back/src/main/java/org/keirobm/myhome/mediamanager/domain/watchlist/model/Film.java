package org.keirobm.myhome.mediamanager.domain.watchlist.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Film {
    private Long id;
    private String title;
    private int year;
    private String folderPath;
    private boolean watched;
}
