package org.keirobm.myhome.mediamanager.infrastructure.emuleClient.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.keirobm.myhome.mediamanager.infrastructure.emuleClient.helper.AmuleProcessHelper;
import org.keirobm.myhome.mediamanager.infrastructure.emuleClient.model.DlItem;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShowDlProcess {

    private static final Pattern DL_FIRST_ROW_REGEX = Pattern.compile("^([\\w\\d]+)\\s+(.*)$");
    private static final Pattern DL_SECOND_ROW_REGEX = Pattern.compile("^\\s*\\[(\\d+(\\.\\d+)?)\\%\\]\\s+(\\d+)\\/\\s*(\\d+).*$");
    private static final Pattern DL_SPEED_REGEX = Pattern.compile(".*\\s+(((\\d+(\\.\\d+)?))\\s*MB\\/s).*$");

    private final AmuleProcessHelper processHelper;

    public List<DlItem> execute() {
        final List<DlItem> result = new ArrayList<>();
        final var output = this.processHelper.executeCommand("show dl");
        if (output.getExitCode() == 0) {
            log.debug("Show dl output: {}", output.getOutput());
            final var reader = new BufferedReader(new StringReader(output.getOutput()));
            
            String line;
            try {
                DlItem currentDl = DlItem.builder().build();
                boolean secondPassed = false;
                while ((line = reader.readLine()) != null) {
                    final var firstMatcher = DL_FIRST_ROW_REGEX.matcher(line);
                    final var secondMatcher = DL_SECOND_ROW_REGEX.matcher(line);
                    final var speedMatcher = DL_SPEED_REGEX.matcher(line);
                    if (firstMatcher.matches()) {
                        currentDl.setHash(firstMatcher.group(1));
                        currentDl.setFilename(firstMatcher.group(2));
                        secondPassed = false;                    
                    } else if (secondMatcher.matches()) {
                        currentDl.setPercentage(Double.parseDouble(secondMatcher.group(1)));
                        currentDl.setSeeders(Integer.parseInt(secondMatcher.group(3)));
                        currentDl.setLeachers(Integer.parseInt(secondMatcher.group(4)));
                        secondPassed = true;
                    }
                    if (speedMatcher.matches()) { 
                        currentDl.setMbSpeed(Double.parseDouble(speedMatcher.group(2)));
                    }
                    if (secondPassed) {
                        result.add(currentDl);
                        currentDl = DlItem.builder().build();
                    }
                }
            } catch (IOException e) {
                log.error("Error occurred while reading show dl output", e);
            }
        }
        return result;
    }

}
