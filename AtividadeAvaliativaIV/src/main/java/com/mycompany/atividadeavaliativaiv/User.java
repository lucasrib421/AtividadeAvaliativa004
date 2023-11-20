package com.mycompany.atividadeavaliativaiv;

import java.time.LocalDate;

public class User extends Entity {

    private static long lastId = 0;
    private String name;
    private String email;
    private LocalDate birthdate;
    private Credential credential;
    private Role role;

    public User() {
        setId(++lastId);
    }

    public User(long userId, String name, String email, LocalDate birthdate, Credential credential, Role role) throws Exception {
        setId(userId);
        setName(name);
        setEmail(email);
        setBirthdate(birthdate);
        setCredential(credential);
        setRole(role);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name == null || name.length() > 150) {
            throw new Exception("O nome deve ter no máximo 150 caracteres e não pode ser nulo.");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email == null || email.length() > 255) {
            throw new Exception("O Email deve ter no máximo 150 caracteres e não pode ser nulo.");
        }
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) throws Exception {
        if (birthdate == null) {
            throw new Exception("A data não pode ser nula.");
        }
        this.birthdate = birthdate;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @Override
    public String toString() {
        return ">> Usuário encontrado: {" +
                "user_id=" + getId() +
                ", name=" + name +
                ", email=" + email +
                ", birthdate=" + birthdate +
                ", username=" + credential.getUsername() +
                ", password=" + credential.getPassword() +
                ", lastAccess=" + credential.getLastAccess() +
                ", credential_id=" + credential.getId() +
                ", role=" + role.getName() +
                ", role_id=" + role.getId() +
                '}';
    }

    private String getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
