package projet.karlo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import projet.karlo.model.Historique;
import projet.karlo.repository.HistoriqueRepository;

@Service
public class HistoriqueService {
    
    @Autowired
    IdGenerator idGenerator ;
    @Autowired
    HistoriqueRepository hRepository ;

    public Historique createHistorique(String description){
        Historique historique = new Historique();

        String idcodes = idGenerator.genererCode();
        String pattern = "yyyy-MM-dd HH:mm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = now.format(formatter);
            historique.setIdHistorique(idcodes);
            historique.setDateHistorique(formattedDateTime);
            historique.setDescription(description);
        return hRepository.save(historique);
    }
        public String deleteHistorique(String id) {
            Historique h = hRepository.findById(id).orElseThrow();

            hRepository.delete(h);
            return "Historique supprimée avec succèss";
        }
}
