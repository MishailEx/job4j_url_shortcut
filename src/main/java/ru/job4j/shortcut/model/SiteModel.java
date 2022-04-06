package ru.job4j.shortcut.model;

public class SiteModel {
    private boolean registration;
    private String login;
    private String password;
    private String description;

    public SiteModel(boolean registration, String login, String password, String description) {
        this.registration = registration;
        this.login = login;
        this.password = password;
        this.description = description;
    }

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
