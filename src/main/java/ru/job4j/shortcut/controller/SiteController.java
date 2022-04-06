package ru.job4j.shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.job4j.shortcut.model.CodeModel;
import ru.job4j.shortcut.model.SiteModel;
import ru.job4j.shortcut.model.StatisticModel;
import ru.job4j.shortcut.model.UrlModel;
import ru.job4j.shortcut.service.SiteService;
import ru.job4j.shortcut.service.UrlService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SiteController {

    private SiteService service;
    private UrlService urlService;

    public SiteController(SiteService service, UrlService urlService) {
        this.service = service;
        this.urlService = urlService;
    }

    @PostMapping("/registration")
    public ResponseEntity<SiteModel> reg(@RequestBody UrlModel site) {
        var access = service.save(site);
        return new ResponseEntity<SiteModel>(
                access,
                HttpStatus.CREATED);
    }

    @PostMapping("/convert")
    public ResponseEntity<CodeModel> convert(@RequestBody UrlModel url, HttpServletRequest request) {
        CodeModel model = urlService.convert(url, request);
        return new ResponseEntity<CodeModel>(
                model,
                HttpStatus.CREATED);
    }

    @GetMapping("/redirect/{code}")
    public RedirectView redirect(@PathVariable String code) {
        return new RedirectView(urlService.updateTotal(code));
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<StatisticModel>> statistic(HttpServletRequest request) {
        return new ResponseEntity<List<StatisticModel>>(urlService.allStatistic(request),
                HttpStatus.ACCEPTED);
    }
}