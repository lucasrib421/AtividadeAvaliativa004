package com.mycompany.atividadeavaliativaiv;

import java.time.LocalDate;
public class Reader extends User {

    public Reader(){
        super();}

    public Reader(Long id, String name, String email, LocalDate birthDate, Credential credential, Role role) throws Exception{
        super(id, name, email, birthDate, credential, role); 
}
} 
