package marconi.com.verifica.services;

import java.util.ArrayList;
import java.util.Optional;

import marconi.com.verifica.domains.Form;


import org.springframework.stereotype.Service;

@Service
public class FilmService {
    
     // creo un finto "database" dove salvare gli utenti registrati
     private ArrayList<Form> users = new ArrayList<>();

     public ArrayList<Form> getUsers() {
         return users;
     }
 
     public void addUser(Form newUser) {
         users.add(newUser);
     }
 
     public Optional<Form> getUserByUsername(String username) {
 
         for(Form u : users) {
             if(u.getUsername().equals(username)) {
                 return Optional.of(u);
             }
         }
         return Optional.empty();
     }
 }
 
    
