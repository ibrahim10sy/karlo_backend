package projet.karlo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import projet.karlo.controller.MarqueController;
import projet.karlo.model.Marque;
import projet.karlo.repository.MarqueRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.util.*;

@Service
public class MarqueService {
    
    @Autowired
    MarqueRepository marqueRepository;
    @Autowired
    IdGenerator idGenerator;

    public Marque createMarque(Marque marque, MultipartFile logoFile) throws Exception{
        Marque m =  marqueRepository.findByNomMarque(marque.getNomMarque());

        if(m != null)
            throw new IllegalStateException("Cette marque existe déjà");

        if (logoFile != null) {
                String imageLocation = "/karlo";
                try {
                    Path imageRootLocation = Paths.get(imageLocation);
                    if (!Files.exists(imageRootLocation)) {
                        Files.createDirectories(imageRootLocation);
                    }
    
                    String imageName = UUID.randomUUID().toString() + "_" + logoFile.getOriginalFilename();
                    Path imagePath = imageRootLocation.resolve(imageName);
                    Files.copy(logoFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
                    // String onlineImagePath =fileUploade.uploadImageToFTP(imagePath, imageName);

                    marque.setLogo(imageName);

                } catch (IOException e) {
                    throw new Exception("Erreur lors du traitement du fichier image : " + e.getMessage());
                }
            }
        String idcodes = idGenerator.genererCode();
        marque.setIdMarque(idcodes);

        return marqueRepository.save(marque);
    }

    public Marque updatMarque(Marque marque, String id, MultipartFile logoFile) throws Exception {
        Marque m = marqueRepository.findById(id).orElseThrow(()->new IllegalStateException("Marque non trouvé"));

        m.setNomMarque(marque.getNomMarque());

        if (logoFile != null) {
            String imageLocation = "/karlo";
            try {
                Path imageRootLocation = Paths.get(imageLocation);
                if (!Files.exists(imageRootLocation)) {
                    Files.createDirectories(imageRootLocation);
                }

                String imageName = UUID.randomUUID().toString() + "_" + logoFile.getOriginalFilename();
                Path imagePath = imageRootLocation.resolve(imageName);
                Files.copy(logoFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
                // String onlineImagePath =fileUploade.uploadImageToFTP(imagePath, imageName);

                m.setLogo(imageName);

            } catch (IOException e) {
                throw new  Exception("Erreur lors du traitement du fichier image : " + e.getMessage());
            }
        }
        return marqueRepository.save(m);
    }
}
