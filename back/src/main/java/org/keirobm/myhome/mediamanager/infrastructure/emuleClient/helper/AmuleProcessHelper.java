package org.keirobm.myhome.mediamanager.infrastructure.emuleClient.helper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.keirobm.myhome.mediamanager.domain.clients.config.AmuleConfig;
import org.keirobm.myhome.mediamanager.infrastructure.emuleClient.model.ProcessOutput;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class AmuleProcessHelper {

    private final AmuleConfig config;
    
    public ProcessOutput executeCommand(String... command) {
        try {
            log.debug("Executing '{}'", String.join(" ", this.buildCommand(command)));
            final StringBuilder outputBuilder = new StringBuilder();
            final StringBuilder errorBuilder = new StringBuilder();
            ProcessBuilder processBuilder = new ProcessBuilder(this.buildCommand(command));
            final var theProcess = processBuilder.start();
            int exitCode = theProcess.waitFor();
            final var processOutputStream = new BufferedReader(new InputStreamReader(theProcess.getInputStream()));
            String output;
            while( processOutputStream.ready() &&
                    (output = processOutputStream.readLine()) != null) {
                outputBuilder.append(output).append("\n");
            }
            processOutputStream.close();
            String errorOutput;
            final var processErrorStream = new BufferedReader(new InputStreamReader(theProcess.getErrorStream()));
            while( processErrorStream.ready() && (errorOutput = processErrorStream.readLine()) != null) {
                errorBuilder.append(errorOutput).append("\n");
            }
            processErrorStream.close();
            return ProcessOutput.builder()
                .exitCode(exitCode)
                .output(outputBuilder.toString())
                .error(errorBuilder.toString())
                .build();
        } catch (Exception e) {
            log.error("Error occurred while executing command '{}': {}", String.join(" ", command), e.getMessage(), e);
            return ProcessOutput.builder()
                .exitCode(-1)
                .output("")
                .error("Error occurred while executing command: " + e.getMessage())
                .build();
        }
    }

    private String[] buildCommand(String... command) {
        List<String> baseCommand = new ArrayList<>(Arrays.asList("amulecmd", "-h", this.config.getHost(),
            "-p", String.valueOf(this.config.getPort()),
             "-P", this.config.getPassword(), "-c"));
        baseCommand.addAll(Arrays.asList(command));
        return baseCommand.toArray(new String[0]);
    }

}
