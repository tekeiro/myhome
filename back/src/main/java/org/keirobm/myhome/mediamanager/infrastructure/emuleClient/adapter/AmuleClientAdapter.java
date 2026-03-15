package org.keirobm.myhome.mediamanager.infrastructure.emuleClient.adapter;

import org.keirobm.myhome.mediamanager.domain.clients.config.AmuleConfig;
import org.keirobm.myhome.mediamanager.domain.clients.port.AmuleClientPort;
import org.keirobm.myhome.mediamanager.infrastructure.emuleClient.helper.AmuleProcessHelper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AmuleClientAdapter implements AmuleClientPort {

    private final AmuleProcessHelper processHelper;

    @Override
    public void search(String queryTerm) {
        log.debug("Searching for: {}", queryTerm);
        final var output = this.processHelper.executeCommand("Search global " + queryTerm);
        if (output.getExitCode() == 0) {
            log.debug("Search output: {}", output.getOutput());
            final var resultOutput = this.processHelper.executeCommand("Results");
            if (resultOutput.getExitCode() == 0) {
                log.debug("Search results: {}", resultOutput.getOutput());
            } else {
                log.error("Failed to get search results with exit code {}: {}", resultOutput.getExitCode(), resultOutput.getError());
            }
         
        } else {
            log.error("Search command failed with exit code {}: {}", output.getExitCode(), output.getError());
        }

    }

}
