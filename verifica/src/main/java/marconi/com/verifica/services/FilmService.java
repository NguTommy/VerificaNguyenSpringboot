package marconi.com.verifica.services;

import java.util.ArrayList;
import java.util.Optional;

import marconi.com.verifica.domains.Form;


import org.springframework.stereotype.Service;

@Service
public class FilmService {
    
     // creo un finto "database" dove salvare gli utenti registrati
     private ArrayList<Form> films = new ArrayList<>();

     public ArrayList<Form> getFilms() {
         return films;
     }

     public FilmService() {
        // aggiungi i prodotti di partenza nell'array
        addFilmIniziali();
    }

   
    private void addFilmIniziali() {
        // aggiungi prodotti di partenza
        Form f1 = new Form();
        f1.setTitolo("Harry Potter");
        f1.setAnno(2001);
        f1.setGenere("Fantasy");
        f1.setCodice("0001");
        f1.setVoto(5);
        addFilm(f1);

        Form f2 = new Form();
        f2.setTitolo("Cars");
        f2.setGenere("Bambini");
        f2.setAnno(2006);
        f2.setVoto(4);
        f2.setCodice("0002");
        addFilm(f2);


    }
    public void addFilm(Form newFilm) {
        films.add(newFilm);
    }

    public void clearFilms() {
        films.clear();
    }

    



 
     public Optional<Form> getFilmByCodice(String codice) {
 
         for(Form c : films) {
             if(c.getCodice().equals(codice)) {
                 return Optional.of(c);
             }
         }
         return Optional.empty();
     }

     
 }
 
    
