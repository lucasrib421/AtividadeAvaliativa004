package com.mycompany.atividadeavaliativaiv;

import java.time.LocalDate;

/**
 *
 * @author Lucas Santos / Gabriel Davi
 */
public class AtividadeAvaliativaIV {
    public static void main(String[] args) throws Exception{
        User testeA = new User();
        
        
        try {
            testeA.setEmail("adsadh@gmail.com");
            testeA.setName("Ana");
            testeA.setBirthdate(LocalDate.now());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        System.out.println("Teste");

    }

}

