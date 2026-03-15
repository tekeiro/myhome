package org.keirobm.myhome.mediamanager.infrastructure.emuleClient.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.keirobm.myhome.mediamanager.domain.search.model.SearchResult;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ParseSearchResults {

    private static final Pattern REGEX = Pattern.compile("^(\\d+)\\.\\s+(.*)\\s+(\\d+(\\.\\d+)?)\\s+(\\d+)$");

    public List<SearchResult> parse(String rawOutput) {
        final List<SearchResult> results = new ArrayList<>();
        final BufferedReader reader = new BufferedReader(new java.io.StringReader(rawOutput));
        boolean parsingTable = false;
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                // Process each line of the output
                if (!parsingTable && line.startsWith("--------")) {
                    parsingTable = true;
                }
                if (parsingTable) {
                    final var matcher =REGEX.matcher(line);
                    if (matcher.matches()) {
                        final var result = SearchResult.builder()
                            .choice(matcher.group(1))
                            .filename(matcher.group(2).trim())
                            .size(Double.parseDouble(matcher.group(3)))
                            .seeds(Integer.parseInt(matcher.group(5)))
                            .build();
                        results.add(result);
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error occurred while parsing search results: {}", e.getMessage(), e);
            return List.of();
        }
        return results;
    }

}
