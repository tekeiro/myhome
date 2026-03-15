package org.keirobm.myhome.mediamanager.infrastructure.emuleClient.adapter;

import java.util.List;

import org.keirobm.myhome.mediamanager.domain.clients.config.AmuleConfig;
import org.keirobm.myhome.mediamanager.domain.clients.port.AmuleClientPort;
import org.keirobm.myhome.mediamanager.domain.queue.model.AcceptDownloadRequest;
import org.keirobm.myhome.mediamanager.domain.queue.model.DownloadQueueItem;
import org.keirobm.myhome.mediamanager.domain.search.model.SearchResult;
import org.keirobm.myhome.mediamanager.infrastructure.emuleClient.helper.AmuleProcessHelper;
import org.keirobm.myhome.mediamanager.infrastructure.emuleClient.process.ParseSearchResults;
import org.keirobm.myhome.mediamanager.infrastructure.emuleClient.process.ShowDlProcess;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AmuleClientAdapter implements AmuleClientPort {

    private final AmuleProcessHelper processHelper;

    private final ParseSearchResults parseSearchResults;

    private final ShowDlProcess showDlProcess;

    @SneakyThrows
    @Override
    public List<SearchResult> search(String queryTerm) {
        log.debug("Searching for: {}", queryTerm);
        final var output = this.processHelper.executeCommand("Search global \"" + queryTerm + "\"");
        if (output.getExitCode() == 0) {
            log.debug("Search output: {}", output.getOutput());
            Thread.sleep(500);
            final var resultOutput = this.processHelper.executeCommand("Results");
            if (resultOutput.getExitCode() == 0) {
                log.debug("Search results: {}", resultOutput.getOutput());
                return this.parseSearchResults.parse(resultOutput.getOutput());
            } else {
                log.error("Failed to get search results with exit code {}: {}", resultOutput.getExitCode(), resultOutput.getError());
                throw new RuntimeException("Failed to get search results: " + resultOutput.getError());
            }
         
        } else {
            log.error("Search command failed with exit code {}: {}", output.getExitCode(), output.getError());
            throw new RuntimeException("Search command failed: " + output.getError());
        }

    }

    @Override
    public DownloadQueueItem initiateDownload(AcceptDownloadRequest acceptDownloadRequest) {
        final var filename = acceptDownloadRequest.getSearchResult().getFilename();
        final var addResult = this.processHelper.executeCommand("Download " + acceptDownloadRequest.getSearchResult().getChoice());
        if (addResult.getExitCode() == 0) {
            log.debug("Download command output: {}", addResult.getOutput());
            final var dlItems = this.showDlProcess.execute();
            final var dlItemFound = dlItems.stream().filter(dlItem -> dlItem.getFilename().equals(filename)).findFirst()
                .orElseThrow(() -> new RuntimeException("Failed to find initiated download: " + filename));
            return DownloadQueueItem.builder()
                    .hash(dlItemFound.getHash())
                    .filename(dlItemFound.getFilename())
                    .percentage(dlItemFound.getPercentage())
                    .contentType(acceptDownloadRequest.getSearchResultType().getContentType())
                    .searchResultType(acceptDownloadRequest.getSearchResultType())
                    .build();
        } else {
            log.error("Failed to initiate download with exit code {}: {}", addResult.getExitCode(), addResult.getError());
            throw new RuntimeException("Failed to initiate download: " + addResult.getError());
        }
    }

    @Override
    public List<DownloadQueueItem> getDownloadQueue() {
        final var dlItems = this.showDlProcess.execute();
        return dlItems.stream().map(dlItem -> {
            return DownloadQueueItem.builder()
                .hash(dlItem.getHash())
                .percentage(dlItem.getPercentage())
                .filename(dlItem.getFilename())
                .mbSpeed(dlItem.getMbSpeed())
                .leachers(dlItem.getLeachers())
                .seeders(dlItem.getSeeders())
                .build();
        }).toList();
    }

}
