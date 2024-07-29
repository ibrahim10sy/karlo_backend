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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import projet.karlo.model.TypeReservoir;
import projet.karlo.service.TypeReservoirService;

@RestController
@RequestMapping("/typeReservoire")
public class TypeReservoireController {

    @Autowired
    TypeReservoirService typeReservoirService;

    @PostMapping("/addTypeReservoir")
    @Operation(summary="Création d'un type de reservoir")
    public ResponseEntity<TypeReservoir> createTypes(@RequestBody TypeReservoir typeReservoir) {
        return new ResponseEntity<>(typeReservoirService.createTypeReservoir(typeReservoir) , HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary="Modification")
    public ResponseEntity<TypeReservoir> updateTypeReservoir(@PathVariable String id, @RequestBody TypeReservoir typeReservoir) {
        return new ResponseEntity<>(typeReservoirService.updateType(typeReservoir, id), HttpStatus.OK);
    }

    @GetMapping("/getAllTypeReservoir")
    @Operation(summary="Liste de tout les voiture")
    public ResponseEntity<List<TypeReservoir>> getAll(){
        return new ResponseEntity<>(typeReservoirService.getVoiture(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary="Supprimé de TypeReservoir")
    public String deleteTypeReservoirs(@PathVariable String id) {
        return typeReservoirService.deleteType(id);
    }
}
