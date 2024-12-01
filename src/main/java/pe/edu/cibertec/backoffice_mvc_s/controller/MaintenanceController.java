package pe.edu.cibertec.backoffice_mvc_s.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDetailDto;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDto;
import pe.edu.cibertec.backoffice_mvc_s.service.MaintenanceService;

import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    @RequestMapping("/start")
    public String start(Model model){

        List<FilmDto> films = maintenanceService.getAllFilms();
        model.addAttribute("films",films);
        return "maintenance";
    }


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model){

        FilmDetailDto filmDetailDto = maintenanceService.getFilmById(id);
        model.addAttribute("filmDetailDto",filmDetailDto);
        return "maintenance-detail";
    }



    @PostMapping("/edit/{id}")
    public String editFilm(@PathVariable Integer id, @ModelAttribute FilmDto filmDto) {
        maintenanceService.updateFilm(id, filmDto);  // Actualiza la película en el servicio
        return "redirect:/maintenance/start";  // Redirige a la lista de películas
    }


}
