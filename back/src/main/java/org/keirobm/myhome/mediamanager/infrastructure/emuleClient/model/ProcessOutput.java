package org.keirobm.myhome.mediamanager.infrastructure.emuleClient.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProcessOutput {
    private int exitCode;
    private String output;
    private String error;
}
