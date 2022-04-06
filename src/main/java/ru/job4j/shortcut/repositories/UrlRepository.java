package ru.job4j.shortcut.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.entity.Site;
import ru.job4j.shortcut.entity.UrlLink;

import java.util.List;

public interface UrlRepository extends CrudRepository<UrlLink, Integer> {
    UrlLink findByUrl(String url);
    UrlLink findByCode(String code);
    List<UrlLink> findAllBySite(Site site);
}
