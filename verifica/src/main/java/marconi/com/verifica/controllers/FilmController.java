package marconi.com.verifica.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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

        return new ModelAndView("homepage");
    }
  
    
}
