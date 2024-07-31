package projet.karlo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import projet.karlo.service.HistoriqueService;

@RestController
@RequestMapping("/historique")
public class HistoriqueController {

    @Autowired
    HistoriqueService hService;

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Suppression de l\'historique")
    public String deleteHistoriques(@PathVariable String id) {
        return hService.deleteHistorique(id);
    }
}
