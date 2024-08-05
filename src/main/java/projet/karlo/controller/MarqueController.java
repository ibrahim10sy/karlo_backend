package projet.karlo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import projet.karlo.model.Marque;
import projet.karlo.service.MarquesService;

@RestController
@RequestMapping("/marque")
public class MarqueController {

    @Autowired
    MarquesService marqueService;

    @PostMapping("/addMarque")
    @Operation(summary = "création d'une marque de voiture")
    public ResponseEntity<Marque> createMarque(
            @Valid @RequestParam("marque") String MarqueString,
            @RequestParam(value = "image", required = false) MultipartFile imageFile)
            throws Exception {
                Marque marque = new Marque();
                try {
                    marque = new JsonMapper().readValue(MarqueString, Marque.class);
                } catch (JsonProcessingException e) {
                    throw new Exception(e.getMessage());
                }
        
                Marque savedMarque = marqueService.createMarque(marque, imageFile);
                System.out.println("Marque controller :" + savedMarque);

                return new ResponseEntity<>(savedMarque, HttpStatus.CREATED);
            }

    @PutMapping("/update/{id}")
    @Operation(summary = "Modification")
    public ResponseEntity<Marque> updateMarque(
            @Valid @RequestParam("marque") String MarqueString,
            @PathVariable String id,
            @RequestParam(value = "image", required = false) MultipartFile imageFile)
            throws Exception {
                Marque marque = new Marque();
                try {
                    marque = new JsonMapper().readValue(MarqueString, Marque.class);
                } catch (JsonProcessingException e) {
                    throw new Exception(e.getMessage());
                }
                Marque savedMarque = marqueService.updateMarque(marque, id, imageFile);

                return new ResponseEntity<>(savedMarque, HttpStatus.CREATED);
            }

    @GetMapping("/getAllMarque")
    @Operation(summary="Liste de tout les voiture")
    public ResponseEntity<List<Marque>> getAll(){
        return new ResponseEntity<>(marqueService.getAllMarque(), HttpStatus.OK);
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMarques(@PathVariable("id") String id) {
        marqueService.deleteMarque(id);
        return  new ResponseEntity<>(HttpStatus.OK); 
    }
}
