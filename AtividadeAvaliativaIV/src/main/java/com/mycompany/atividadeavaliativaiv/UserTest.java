/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/


package com.mycompany.atividadeavaliativaiv;

public class UserTest {
    public static void main(String[] args) throws Exception{
        // Inclusão de um usuário com papel "Bibliotecário" e respectiva credencial
        Role bibliotecarioRole = new Role();
        bibliotecarioRole.setName("Bibliotecário");

        RoleDao roleDao = new RoleDao();
        roleDao.saveOrUpdate(bibliotecarioRole);

        User bibliotecario = new User();
        bibliotecario.setName("Nome do Bibliotecário");
        bibliotecario.setEmail("bibliotecario@example.com");
        bibliotecario.setRole(bibliotecarioRole);

        CredentialDao credentialDao = new CredentialDao();
        credentialDao.saveOrUpdate(bibliotecario.getCredential());

        LibrarianDao librarianDao = new LibrarianDao();
        librarianDao.saveOrUpdate(bibliotecario);

        // Atualização do email
        bibliotecario.setEmail("novobibliotecario@example.com");
        librarianDao.saveOrUpdate(bibliotecario);

        // Recuperação deste usuário
        User retrievedBibliotecario = librarianDao.findById(bibliotecario.getId());
        System.out.println("Usuário recuperado: " + retrievedBibliotecario);
        
        
        
        ReaderDao readerDao = new ReaderDao();
        List<Reader> leitores = readerDao.findAll();
        List<User> usuarios = new ArrayList<>();
        
        for (Reader leitor : leitores) {
            System.out.println("Leitor encontrado: " + leitor);
        }

    
        
        CredentialDao credentialDao = new CredentialDao();
        
        for (User usuario : usuarios) {
            Credential credential = usuario.getCredential();

            if (credentialDao.authenticate(credential) != null) {
                System.out.println("Usuário autenticado: " + usuario);
            } else {
                System.out.println("Falha na autenticação para o usuário: " + usuario);
        }
        

        }
}
}

 