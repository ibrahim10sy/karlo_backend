package projet.karlo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;
import projet.karlo.model.Marque;
import projet.karlo.model.TypeReservoir;
import projet.karlo.model.TypeVoiture;
import projet.karlo.model.User;
import projet.karlo.model.VoitureLouer;
import projet.karlo.model.VoitureLouer;
import projet.karlo.repository.MarqueRepository;
import projet.karlo.repository.TypeReservoirRepository;
import projet.karlo.repository.TypeVoitureRepository;
import projet.karlo.repository.UserRepository;
import projet.karlo.repository.VoitureLouerRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class VoitureLouerService {
    
    @Autowired
    IdGenerator idGenerator ;
    @Autowired
    VoitureLouerRepository voitureLouerRepository;
    @Autowired
    UserRepository userRepository ;
    @Autowired
    TypeReservoirRepository typeReservoirRepository;
    @Autowired
    TypeVoitureRepository typeVoitureRepository;
    @Autowired
    MarqueRepository marqueRepository;

    public VoitureLouer createVoiture(VoitureLouer vLouer, MultipartFile imageFile) throws Exception{
        User user  = userRepository.findByIdUser(vLouer.getUser().getIdUser());

        TypeVoiture type = typeVoitureRepository.findById(vLouer.getTypeVoiture().getIdTypeVoiture()).orElseThrow();

        TypeReservoir typeRe = typeReservoirRepository.findById(vLouer.getTypeReservoir().getIdTypeReservoir()).orElseThrow();

        Marque marque = marqueRepository.findById(vLouer.getMarque().getIdMarque()).orElseThrow();

        if(marque == null)
            throw new IllegalStateException("Aucune marque de voiture trouvée");
    
        if(user == null)
            throw new IllegalStateException("Aucun utilisateur trouvée");

        if(type == null)
            throw new IllegalStateException("Aucun type de voiture trouvée");

        if(typeRe == null)
            throw new IllegalStateException("Aucun type de reservoir trouvé trouvée");
        
         // Traitement du fichier image 
            if (imageFile != null) {
                String imageLocation = "/ais";
                try {
                    Path imageRootLocation = Paths.get(imageLocation);
                    if (!Files.exists(imageRootLocation)) {
                        Files.createDirectories(imageRootLocation);
                    }
    
                    String imageName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                    Path imagePath = imageRootLocation.resolve(imageName);
                    Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
                    // String onlineImagePath =uploadeAlerte.uploadImageToFTP(imagePath, imageName);

                    vLouer.setPhoto(imageName);
                } catch (IOException e) {
                    throw new Exception("Erreur lors du traitement du fichier image : " + e.getMessage());
                }
            }
            return voitureLouerRepository.save(vLouer);
    }

    public VoitureLouer updateVoiture(VoitureLouer vlouer, String id, MultipartFile imageFile) throws Exception{
        VoitureLouer v = voitureLouerRepository.findById(id).orElseThrow(()-> new IllegalStateException("Voiture non trouvé"));

        v.setModele(vlouer.getModele());
        v.setMatricule(vlouer.getMatricule());
        v.setAnnee(vlouer.getAnnee());
        v.setTypeBoite(vlouer.getTypeBoite());
        v.setNbPortiere(vlouer.getNbPortiere());
        v.setPrixProprietaire(vlouer.getPrixProprietaire());
        v.setPrixAugmente(vlouer.getPrixAugmente());
        v.setIsChauffeur(vlouer.getIsChauffeur());

        if(vlouer.getTypeVoiture() != null){
            v.setTypeVoiture(vlouer.getTypeVoiture());
        }

        if(vlouer.getTypeReservoir() != null){
            v.setTypeReservoir(vlouer.getTypeReservoir());
        }

        if(vlouer.getMarque() != null){
            v.setMarque(vlouer.getMarque());
        }

         // Traitement du fichier image
         if (imageFile != null) {
            String imageLocation = "/ais";
            try {
                Path imageRootLocation = Paths.get(imageLocation);
                if (!Files.exists(imageRootLocation)) {
                    Files.createDirectories(imageRootLocation);
                }

                String imageName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path imagePath = imageRootLocation.resolve(imageName);
                Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
                // String onlineImagePath =uploadeAlerte.uploadImageToFTP(imagePath, imageName);

                v.setPhoto(imageName);
            } catch (IOException e) {
                throw new Exception("Erreur lors du traitement du fichier image : " + e.getMessage());
            }
        }
        return voitureLouerRepository.save(v);
    }


    public List<VoitureLouer> getAllVoiture(){
        List<VoitureLouer> voitureList = voitureLouerRepository.findAll();

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureLouer::getDateAjout));

        return voitureList;
    }

    public String deleteVoiture(String id){
        VoitureLouer v = voitureLouerRepository.findById(id).orElseThrow(()-> new IllegalStateException("Voiture non trouvée"));

        voitureLouerRepository.delete(v);
        return "Supprimé avec succès";
    }

     public VoitureLouer updateNbViev(String id) throws Exception {
        Optional<VoitureLouer> voitureOpt = voitureLouerRepository.findById(id);
        int count = voitureOpt.get().getNbreView();

        if (voitureOpt.isPresent()) {
            VoitureLouer VoitureLouer = voitureOpt.get();
            VoitureLouer.setNbreView(count);

            return voitureLouerRepository.save(VoitureLouer);
        } else {
            throw new Exception("Une erreur s'est produite");
        }
    }
}
