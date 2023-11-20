package com.mycompany.atividadeavaliativaiv;

import java.time.LocalDate;

public class Librarian extends User {

    public Librarian(){
        super();
    }

    public Librarian(Long id, String name, String email, LocalDate birthDate, Credential credential, Role role) throws Exception{
        super(id, name, email, birthDate, credential, role);
    }

}