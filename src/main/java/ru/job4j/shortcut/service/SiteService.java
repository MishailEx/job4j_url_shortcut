package ru.job4j.shortcut.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.entity.Site;
import ru.job4j.shortcut.entity.UrlLink;
import ru.job4j.shortcut.model.SiteModel;
import ru.job4j.shortcut.model.UrlModel;
import ru.job4j.shortcut.repositories.SiteRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Service
public class SiteService {
    private SiteRepository store;
    private BCryptPasswordEncoder encoder;

    public SiteService(SiteRepository store, BCryptPasswordEncoder encoder) {
        this.store = store;
        this.encoder = encoder;
    }


    public SiteModel save(UrlModel site) {
        SiteModel siteModel = new SiteModel(false,
                null, null, "такой сайт уже существует");
        Site siteFind = findBySite(site.getUrl());
        if (siteFind == null) {
            siteFind = new Site();
            String login = checkCode(Code.generate(8));
            String password = Code.generate(8);
            siteFind.setPassword(encoder.encode(password));
            siteFind.setLogin(login);
            siteFind.setSite(site.getUrl());
            store.save(siteFind);
            return new SiteModel(true, login, password, "created");
        }
        return siteModel;
    }

    public Site findBySite(String name) {
        return store.findBySite(name);
    }

    private String checkCode(String code) {
        Site site = store.findByLogin(code);
        if (site != null) {
            String c = Code.generate(8);
            checkCode(c);
        }
        return code;
    }
}
