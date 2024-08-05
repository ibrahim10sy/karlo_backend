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

import projet.karlo.model.VoitureVendre;
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
import java.time.format.DateTimeFormatter;
import java.util.*;

import projet.karlo.model.Image;

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
    @Autowired
    HistoriqueService historiqueService;

    public VoitureLouer createVoiture(VoitureLouer vLouer, List<MultipartFile> imageFiles) throws Exception{
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
        
          // Traitement des fichiers images
        if (imageFiles != null && !imageFiles.isEmpty()) {
        String imageLocation = "C:\\xampp\\htdocs\\karlo";
        Path imageRootLocation = Paths.get(imageLocation);
        if (!Files.exists(imageRootLocation)) {
            Files.createDirectories(imageRootLocation);
        }

        for (MultipartFile imageFile : imageFiles) {
            if (!imageFile.isEmpty()) {
                String imageName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path imagePath = imageRootLocation.resolve(imageName);
                Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

                Image imageVoiture = new Image();
                imageVoiture.setImageName(imageName);
                imageVoiture.setImagePath("/karlo" + imagePath.toString());
                imageVoiture.setVoitureLouer(vLouer);
                vLouer.getImages().add(imageVoiture);
            }
        }
    }
    String idcodes = idGenerator.genererCode();
    String pattern = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        vLouer.setIdVoiture(idcodes);
        vLouer.setDateAjout(formattedDateTime);
        historiqueService.createHistorique("Ajout de voiture de location : " + vLouer.getModele() + "matricule : " + vLouer.getMatricule());

        return voitureLouerRepository.save(vLouer);
    }

    public VoitureLouer updateVoiture(VoitureLouer vlouer, String id, List<MultipartFile> imageFiles) throws Exception {
        VoitureLouer v = voitureLouerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Voiture non trouvée"));
    
        v.setModele(vlouer.getModele());
        v.setMatricule(vlouer.getMatricule());
        v.setAnnee(vlouer.getAnnee());
        v.setTypeBoite(vlouer.getTypeBoite());
        v.setNbPortiere(vlouer.getNbPortiere());
        v.setPrixProprietaire(vlouer.getPrixProprietaire());
        v.setPrixAugmente(vlouer.getPrixAugmente());
        v.setIsChauffeur(vlouer.getIsChauffeur());
    
        if (vlouer.getTypeVoiture() != null) {
            v.setTypeVoiture(vlouer.getTypeVoiture());
        }
    
        if (vlouer.getTypeReservoir() != null) {
            v.setTypeReservoir(vlouer.getTypeReservoir());
        }
    
        if (vlouer.getMarque() != null) {
            v.setMarque(vlouer.getMarque());
        }
    
        // Traitement des fichiers images
        if (imageFiles != null && !imageFiles.isEmpty()) {
            String imageLocation = "C:\\xampp\\htdocs\\karlo";
            Path imageRootLocation = Paths.get(imageLocation);
            if (!Files.exists(imageRootLocation)) {
                Files.createDirectories(imageRootLocation);
            }
    
            for (MultipartFile imageFile : imageFiles) {
                if (!imageFile.isEmpty()) {
                    String imageName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                    Path imagePath = imageRootLocation.resolve(imageName);
                    Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
    
                    Image imageVoiture = new Image();
                    imageVoiture.setImageName(imageName);
                    imageVoiture.setImagePath("/karlo" + imagePath.toString());
                    imageVoiture.setVoitureLouer(v);
                    v.getImages().add(imageVoiture);
                }
            }
        }
        historiqueService.createHistorique("Modification  de voiture de location : " + v.getModele() + "matricule : " + v.getMatricule());

        return voitureLouerRepository.save(v);
    }
    


    public List<VoitureLouer> getAllVoiture(){
        List<VoitureLouer> voitureList = voitureLouerRepository.findAll();

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureLouer::getDateAjout));

        return voitureList;
    }

      public List<VoitureLouer> getAllVoitureByMarque(String nom){
        List<VoitureLouer> voitureList = voitureLouerRepository.findByMarque_NomMarque(nom);

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureLouer::getDateAjout));

        return voitureList;
    }

    public List<VoitureLouer> getAllVoitureByTypeVoiture(String nom){
        List<VoitureLouer> voitureList = voitureLouerRepository.findByTypeVoiture_NomTypeVoiture(nom);

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureLouer::getDateAjout));

        return voitureList;
    }

    public List<VoitureLouer> getAllVoitureByTypeReservoir(String nom){
        List<VoitureLouer> voitureList = voitureLouerRepository.findByTypeReservoir_NomTypeReservoir(nom);

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureLouer::getDateAjout));

        return voitureList;
    }

    public List<VoitureLouer> getAllVoitureByTypeBoite(String nom){
        List<VoitureLouer> voitureList = voitureLouerRepository.findByTypeBoite(nom);

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureLouer::getDateAjout));

        return voitureList;
    }

    public List<VoitureLouer> getAllVoitureByAnnee(String annee){
        List<VoitureLouer> voitureList = voitureLouerRepository.findByAnnee(annee);

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureLouer::getDateAjout));

        return voitureList;
    }

    public List<VoitureLouer> getAllVoitureByNbreView(){
        List<VoitureLouer> voitureList = voitureLouerRepository.findAllByOrderByNbreViewDesc();

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureLouer::getDateAjout));

        return voitureList;
    }


    public List<VoitureLouer> getAllVoitureByPrixAugmenter(){
        List<VoitureLouer> voitureList = voitureLouerRepository.findAllByOrderByPrixAugmenteDesc();

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureLouer::getDateAjout));

        return voitureList;
    }

    public List<VoitureLouer> getAllVoitureByPrixAugmenterMoinsChere(){
        List<VoitureLouer> voitureList = voitureLouerRepository.findAllByOrderByPrixAugmenteAsc();

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureLouer::getDateAjout));

        return voitureList;
    }

    public String deleteVoiture(String id){
        VoitureLouer v = voitureLouerRepository.findById(id).orElseThrow(()-> new IllegalStateException("Voiture non trouvée"));
        historiqueService.createHistorique("Suppression de la  voiture de location : " + v.getModele() + "matricule : " + v.getMatricule());

        voitureLouerRepository.delete(v);
        return "Supprimé avec succès";
    }

    //  public VoitureLouer updateNbViev(String id) throws Exception {
    //     Optional<VoitureLouer> voitureOpt = voitureLouerRepository.findById(id);
    //     int count = voitureOpt.get().getNbreView();

    //     if (voitureOpt.isPresent()) {
    //         VoitureLouer VoitureLouer = voitureOpt.get();
    //         VoitureLouer.setNbreView(count);
     public VoitureLouer updateNbViev(String id) throws Exception {
        Optional<VoitureLouer> voitureOpt = voitureLouerRepository.findById(id);
        
        if (voitureOpt.isPresent()) {
            VoitureLouer VoitureLouer = voitureOpt.get();
            int count = VoitureLouer.getNbreView() + 1;
            VoitureLouer.setNbreView(count);

            return voitureLouerRepository.save(VoitureLouer);
        } else {
            throw new Exception("Une erreur s'est produite");
        }
    }
}
