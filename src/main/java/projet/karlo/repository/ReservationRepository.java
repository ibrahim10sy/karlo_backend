package projet.karlo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.karlo.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String>{

    List<Reservation> findByDateDebut(String date);
    List<Reservation> findByDateFin(String date);
    List<Reservation> findByNomClient(String nom);
}
