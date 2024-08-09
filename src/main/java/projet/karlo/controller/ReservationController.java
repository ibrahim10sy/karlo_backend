package projet.karlo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import projet.karlo.model.Reservation;
import projet.karlo.model.VoitureLouer;
import projet.karlo.service.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping("/addReservation")
    @Operation(summary = "Ajout d'une reservation")
    public ResponseEntity<Reservation> createReser(
            @Valid @RequestParam("reservation") String reservationString,
            @RequestParam(value = "images", required = false) List<MultipartFile> imageFiles)
            throws Exception {
        Reservation reservation = new Reservation();
        try {
            reservation = new JsonMapper().readValue(reservationString, Reservation.class);
        } catch (JsonProcessingException e) {
            throw new Exception(e.getMessage());
        }
    
        Reservation savedreservation = reservationService.createReservation(reservation, imageFiles);
        System.out.println(" controller :" + savedreservation);
    
        return new ResponseEntity<>(savedreservation, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Modification d'une reservation")
    public ResponseEntity<Reservation> updateRes(
            @Valid @RequestParam("reservation") String reservationString,
            @RequestParam(value = "images", required = false) List<MultipartFile> imageFiles)
            throws Exception {
        Reservation reservation = new Reservation();
        try {
            reservation = new JsonMapper().readValue(reservationString, Reservation.class);
        } catch (JsonProcessingException e) {
            throw new Exception(e.getMessage());
        }
    
        Reservation savedreservation = reservationService.createReservation(reservation, imageFiles);
        System.out.println(" controller :" + savedreservation);
    
        return new ResponseEntity<>(savedreservation, HttpStatus.CREATED);
    }

     @GetMapping("/getAllReservation")
     @Operation(summary="Liste de tout les reservations")
      public ResponseEntity<List<Reservation>> getAllReservation(){
                return new ResponseEntity<>(reservationService.getAllReservation(),HttpStatus.OK);
    }

     @GetMapping("/getAllReservationByClient/{nomClient}")
     @Operation(summary="Liste de tout les reservations")
      public ResponseEntity<List<Reservation>> getAllReservationByClt(@PathVariable String nom){
                return new ResponseEntity<>(reservationService.getAllReservationByClient(nom),HttpStatus.OK);
    }

    @GetMapping("/getAllReservationVyDateDebut/{date}")
    @Operation(summary="Liste de tout les reservations")
     public ResponseEntity<List<Reservation>> getAllReservationByDateDebut(@PathVariable String date){
               return new ResponseEntity<>(reservationService.getAllReservationByDateDebut(date),HttpStatus.OK);
   }

    @GetMapping("/getAllReservationVyDateFin/{date}")
    @Operation(summary="Liste de tout les reservations")
     public ResponseEntity<List<Reservation>> getAllReservationByDateFin(@PathVariable String date){
               return new ResponseEntity<>(reservationService.getAllReservationByDateFin(date),HttpStatus.OK);
   }


   @DeleteMapping("/delete/{id}")
   @Operation(summary="Suppression d'une reservation de voiture")
    public ResponseEntity<Void> deleteRes(@PathVariable("id") String id) {
        reservationService.deleteRes(id);
    return  new ResponseEntity<>(HttpStatus.OK); 
    }
    
}
