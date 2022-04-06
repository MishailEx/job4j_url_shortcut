package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.entity.Site;
import ru.job4j.shortcut.entity.UrlLink;
import ru.job4j.shortcut.model.CodeModel;
import ru.job4j.shortcut.model.StatisticModel;
import ru.job4j.shortcut.model.UrlModel;
import ru.job4j.shortcut.repositories.SiteRepository;
import ru.job4j.shortcut.repositories.UrlRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UrlService {
    private SiteRepository siteRepository;
    private UrlRepository urlRepository;

    public UrlService(SiteRepository siteRepository, UrlRepository urlRepository) {
        this.siteRepository = siteRepository;
        this.urlRepository = urlRepository;
    }

    public UrlLink findByCode(String code) {
        return urlRepository.findByCode(code);
    }

    public CodeModel convert(UrlModel url, HttpServletRequest request) {
        CodeModel model = new CodeModel();
        String login = request.getRemoteUser();
        Site site = siteRepository.findByLogin(login);
        UrlLink link = urlRepository.findByUrl(url.getUrl());
        if (link != null) {
            model.setCode(link.getCode());
            return model;
        }
        link = new UrlLink();
        link.setSite(site);
        link.setUrl(url.getUrl());
        String code = checkCode(Code.generate(8));
        link.setCode(code);
        urlRepository.save(link);
        model.setCode(code);
        return model;
    }

    private String checkCode(String code) {
        UrlLink url = urlRepository.findByUrl(code);
        if (url != null) {
            String c = Code.generate(8);
            checkCode(c);
        }
        return code;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String updateTotal(String code) {
        UrlLink link = urlRepository.findByCode(code);
        link.setTotal(link.getTotal() + 1);
        urlRepository.save(link);
        return link.getUrl();
    }

    public List<StatisticModel> allStatistic(HttpServletRequest request) {
        String login = request.getRemoteUser();
        Site site = siteRepository.findByLogin(login);
        List<StatisticModel> list = new ArrayList<>();
        List<UrlLink> links = urlRepository.findAllBySite(site);
        links.forEach(a -> list.add(new StatisticModel(a.getUrl(), a.getTotal())));
        return list;
    }
}
