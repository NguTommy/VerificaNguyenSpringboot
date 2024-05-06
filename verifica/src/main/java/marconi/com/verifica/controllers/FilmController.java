package marconi.com.verifica.controllers;


import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import marconi.com.verifica.domains.Form;   
import marconi.com.verifica.services.FilmService;   


@Controller
@RequestMapping("/")
public class FilmController {

      // no db ma va bene lo stesso
      @Autowired
      FilmService filmService;

      @GetMapping
    public ModelAndView viewHomePage() {

        return new ModelAndView("catalogo");
    }

    



    @GetMapping("/films")
    public ModelAndView handlerUserAction(@RequestParam("type") String type) {
        if (type.equals("nuovo")) {
            return new ModelAndView("filmRegistration").addObject("filmForm", new Form());
        } else if (type.equals("svuota")) {
            filmService.clearFilms();
            ModelAndView modelAndView = new ModelAndView("redirect:/");
            modelAndView.addObject("messaggio", "Catalogo svuotato con successo.");
            return modelAndView;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagina non trovata!");
        }
    }


    
    @PostMapping("/films/nuovo")
    public ModelAndView handlerNewUser(@ModelAttribute Form filmForm) {

        // salvo l'utente nel "database"
        filmService.addFilm(filmForm); 

        String codice = filmForm.getCodice();
        //pattern prg per reindirizzare la pagina ad una get         
        return new ModelAndView("redirect:/films/" + codice);
    }

    @PostMapping("/films/svuota")
    public String svuotaCatalogo(RedirectAttributes redirectAttributes) {
        filmService.clearFilms();
        redirectAttributes.addFlashAttribute("Messaggio", "Il catalogo è stato svuotato con successo.");
        return "redirect:/films";
    }

    @PostMapping("/films/svuota/{titolo}")
    public String eliminaFilm(RedirectAttributes redirectAttributes) {
        filmService.clearFilms();
        redirectAttributes.addFlashAttribute("Messaggio", "Il film è stato eliminato con successo.");
        return "redirect:/films";
    }

    @GetMapping("/films/{codice}")
    public  ModelAndView filmDetail(@PathVariable("codice") String codice) {

        Optional<Form> film = filmService.getFilmByCodice(codice);

        // se l'utente esiste, mostro la pagina di recap
        if (film.isPresent())
            return new ModelAndView("film-detail").addObject("film", film.get());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film non trovato!");
    }

    



/* 
    @GetMapping("catalogo")
    public String catalogo(Model model) {
        List<Form> filmList = filmService.getFilms();
        model.addAttribute("productList", filmList);
        return "catalogo";
    }
  */
  
  @GetMapping("/catalogo")
  public ModelAndView catalogo() {
      List<Form> productList = filmService.getFilms();
      return new ModelAndView("catalogo").addObject("productList", productList);
  }


    


  
    
}
