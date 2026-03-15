package org.keirobm.myhome.mediamanager.apirest.controllers;

import org.keirobm.myhome.mediamanager.domain.clients.port.AmuleClientPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/v1/api/test", consumes = "application/json", produces = "application/json")
@RequiredArgsConstructor
public class TestController {

    private final AmuleClientPort amuleClient;

    @GetMapping("search")
    public String testSearch(@RequestParam("q") String queryTerm) {
        amuleClient.search(queryTerm);
        return "Search executed for: " + queryTerm;
    }

}
