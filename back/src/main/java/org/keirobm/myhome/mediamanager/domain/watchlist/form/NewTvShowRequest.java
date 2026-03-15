package org.keirobm.myhome.mediamanager.domain.watchlist.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class NewTvShowRequest {
    private String title;
    private int year;
}
