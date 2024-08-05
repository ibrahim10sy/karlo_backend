package projet.karlo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import projet.karlo.model.Historique;
import projet.karlo.model.User;
import projet.karlo.service.HistoriqueService;

@RestController
@RequestMapping("/historique")
public class HistoriqueController {

    @Autowired
    HistoriqueService hService;

     @GetMapping("/getAllHistorique")
    @Operation(summary="Liste de tout les historiques")
    public ResponseEntity<List<Historique>> getAllHistorique(){
        return new ResponseEntity<>(hService.getAllHistoriques(), HttpStatus.OK);
    }

    
   
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHistoriques(@PathVariable("id") String id) {
        hService.deleteHistorique(id);
        return  new ResponseEntity<>(HttpStatus.OK); 
    }
}
