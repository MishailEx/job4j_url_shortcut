package ru.job4j.shortcut.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.shortcut.entity.Site;

@Repository
public interface SiteRepository extends CrudRepository<Site, Integer> {
    Site findBySite(String site);
    Site findByLogin(String login);
}
