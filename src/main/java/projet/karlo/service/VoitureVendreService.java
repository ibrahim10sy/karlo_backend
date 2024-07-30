package projet.karlo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;
import projet.karlo.model.Image;
import projet.karlo.model.Marque;
import projet.karlo.model.TypeReservoir;
import projet.karlo.model.TypeVoiture;
import projet.karlo.model.User;
import projet.karlo.model.VoitureVendre;
import projet.karlo.repository.MarqueRepository;
import projet.karlo.repository.TypeReservoirRepository;
import projet.karlo.repository.TypeVoitureRepository;
import projet.karlo.repository.UserRepository;
import projet.karlo.repository.VoitureVendreRepository;

@Service
public class VoitureVendreService {
    @Autowired
    IdGenerator idGenerator ;
    @Autowired
    VoitureVendreRepository voitureVendreRepository;
    @Autowired
    UserRepository userRepository ;
    @Autowired
    TypeReservoirRepository typeReservoirRepository;
    @Autowired
    TypeVoitureRepository typeVoitureRepository;
    @Autowired
    MarqueRepository marqueRepository;

    public VoitureVendre createVoiture(VoitureVendre vLouer,List<MultipartFile> imageFiles) throws Exception{
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
                imageVoiture.setVoitureVendre(vLouer);
                vLouer.getImages().add(imageVoiture);
            }
        }
    }
            return voitureVendreRepository.save(vLouer);
    }

    public VoitureVendre updateVoiture(VoitureVendre vlouer, String id,  List<MultipartFile> imageFiles) throws Exception{
        VoitureVendre v = voitureVendreRepository.findById(id).orElseThrow(()-> new IllegalStateException("Voiture non trouvé"));

        v.setModele(vlouer.getModele());
        v.setMatricule(vlouer.getMatricule());
        v.setAnnee(vlouer.getAnnee());
        v.setTypeBoite(vlouer.getTypeBoite());
        v.setNbPortiere(vlouer.getNbPortiere());
        v.setPrixProprietaire(vlouer.getPrixProprietaire());
        v.setPrixAugmente(vlouer.getPrixAugmente());

        if(vlouer.getTypeVoiture() != null){
            v.setTypeVoiture(vlouer.getTypeVoiture());
        }

        if(vlouer.getTypeReservoir() != null){
            v.setTypeReservoir(vlouer.getTypeReservoir());
        }

        if(vlouer.getMarque() != null){
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
                    imageVoiture.setVoitureVendre(v);
                    v.getImages().add(imageVoiture);
                }
            }
        }
        return voitureVendreRepository.save(v);
    }


    public List<VoitureVendre> getAllVoiture(){
        List<VoitureVendre> voitureList = voitureVendreRepository.findAll();

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureVendre::getDateAjout));

        return voitureList;
    }

    public List<VoitureVendre> getAllVoitureByMarque(String nom){
        List<VoitureVendre> voitureList = voitureVendreRepository.findByMarque_NomMarque(nom);

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureVendre::getDateAjout));

        return voitureList;
    }

    public List<VoitureVendre> getAllVoitureByTypeVoiture(String nom){
        List<VoitureVendre> voitureList = voitureVendreRepository.findByTypeVoiture_NomTypeVoiture(nom);

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureVendre::getDateAjout));

        return voitureList;
    }

    public List<VoitureVendre> getAllVoitureByTypeReservoir(String nom){
        List<VoitureVendre> voitureList = voitureVendreRepository.findByTypeReservoir_NomTypeReservoir(nom);

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureVendre::getDateAjout));

        return voitureList;
    }

    public List<VoitureVendre> getAllVoitureByTypeBoite(String nom){
        List<VoitureVendre> voitureList = voitureVendreRepository.findByTypeBoite(nom);

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureVendre::getDateAjout));

        return voitureList;
    }

    public List<VoitureVendre> getAllVoitureByAnnee(String annee){
        List<VoitureVendre> voitureList = voitureVendreRepository.findByAnnee(annee);

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureVendre::getDateAjout));

        return voitureList;
    }

    public List<VoitureVendre> getAllVoitureByNbreView(){
        List<VoitureVendre> voitureList = voitureVendreRepository.findAllByOrderByNbreViewDesc();

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureVendre::getDateAjout));

        return voitureList;
    }


    public List<VoitureVendre> getAllVoitureByPrixAugmenter(){
        List<VoitureVendre> voitureList = voitureVendreRepository.findAllByOrderByPrixAugmenteDesc();

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureVendre::getDateAjout));

        return voitureList;
    }

    public List<VoitureVendre> getAllVoitureByPrixAugmenterMoinsChere(){
        List<VoitureVendre> voitureList = voitureVendreRepository.findAllByOrderByPrixAugmenteAsc();

        if (voitureList.isEmpty())
            throw new EntityNotFoundException("Aucune voiture trouvée");

        voitureList.sort(Comparator.comparing(VoitureVendre::getDateAjout));

        return voitureList;
    }


    public String deleteVoiture(String id){
        VoitureVendre v = voitureVendreRepository.findById(id).orElseThrow(()-> new IllegalStateException("Voiture non trouvée"));

        voitureVendreRepository.delete(v);
        return "Supprimé avec succès";
    }

    public VoitureVendre updateNbViev(String id) throws Exception {
        Optional<VoitureVendre> voitureOpt = voitureVendreRepository.findById(id);
        int count = voitureOpt.get().getNbreView();

        if (voitureOpt.isPresent()) {
            VoitureVendre voitureVendre = voitureOpt.get();
            voitureVendre.setNbreView(count);

            return voitureVendreRepository.save(voitureVendre);
        } else {
            throw new Exception("Une erreur s'est produite");
        }
    }
}
