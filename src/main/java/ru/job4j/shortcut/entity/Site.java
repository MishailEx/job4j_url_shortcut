package ru.job4j.shortcut.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "site")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String site;
    private String login;
    private String password;
    @OneToMany(mappedBy = "site")
    private List<UrlLink> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String username) {
        this.login = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Site site = (Site) o;
        return Objects.equals(login, site.login) && Objects.equals(password, site.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
