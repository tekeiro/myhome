package org.keirobm.myhome.mediamanager.infrastructure.emuleClient.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class DlItem {
    private String hash;
    private String filename;
    private double percentage;
    private double mbSpeed;
    private int leachers;
    private int seeders;
}
