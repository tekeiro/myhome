package org.keirobm.myhome.mediamanager.apirest.controllers;

import java.util.List;

import org.keirobm.myhome.mediamanager.application.queue.GetDownloadQueueUseCase;
import org.keirobm.myhome.mediamanager.domain.queue.model.DownloadQueueItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(value = "v1/api/queue", consumes = "application/json", produces = "application/json")
@RequiredArgsConstructor
public class DownloadQueueController {

    private final GetDownloadQueueUseCase getDownloadQueueUseCase;

    @GetMapping("")
    public List<DownloadQueueItem> getDownloadQueue() {
        return this.getDownloadQueueUseCase.getDownloadQueue();
    }
    

}
