package com.mycompany.atividadeavaliativaiv;

import java.time.LocalDate;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Credential extends Entity {

    private String username;
    private String password;
    private LocalDate lastAccess;
    private boolean enabled = true;
    private User user;

    public Credential() {
    }

    public Credential(Long credentialId, String username, String password, LocalDate lastAccess, boolean enabled, User user) throws Exception {
        setId(credentialId);
        setUsername(username);
        setPassword(password);
        setLastAccess(lastAccess);
        setEnabled(enabled);
        setUser(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.setId(user.getId());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception {
        if (username == null || username.length() > 15) {
            throw new Exception("O user deve ter no máximo 15 caracteres / não pode ser nulo.");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if (password == null || password.length() > 32) {
            throw new Exception("A senha deve possuir no máximo 32 caracteres e não pode ser nula.");
        }
        this.password = password;
    }

    public LocalDate getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDate lastAccess) {
        this.lastAccess = lastAccess;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return ">> Credencial encontrada: {" +
                "username=" + username +
                ", password=" + password +
                ", lastAccess=" + lastAccess +
                ", enabled=" + enabled +
                ", name=" + user.getName() +
                ", email=" + user.getEmail() +
                ", birthdate=" + user.getBirthdate() +
                ", user_id=" + user.getId() +
                ", credential_id=" + getId() +
                '}';
    }
}
