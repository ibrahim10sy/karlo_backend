package projet.karlo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.karlo.model.TypeReservoir;
import projet.karlo.repository.TypeReservoirRepository;

@Service
public class TypeReservoirService {
    @Autowired
    IdGenerator idGenerator ;
    @Autowired
    TypeReservoirRepository typeReservoirRepository;

    public TypeReservoir createTypeReservoir(TypeReservoir typeReservoir){
        TypeReservoir r = typeReservoirRepository.findByNomTypeReservoir(typeReservoir.getNomTypeReservoir());

        if(r != null)
            throw new IllegalStateException("Ce type existe déjà");

        String idcodes = idGenerator.genererCode();
        typeReservoir.setIdTypeReservoir(idcodes);

        return typeReservoirRepository.save(typeReservoir);
    }


    public TypeReservoir updateType(TypeReservoir typeReservoir, String id){
        TypeReservoir t = typeReservoirRepository.findById(id).orElseThrow(()-> new IllegalStateException("Not found"));

        t.setNomTypeReservoir(typeReservoir.getNomTypeReservoir());
        t.setDescription(typeReservoir.getDescription());

        return typeReservoirRepository.save(t);
    }

    public List<TypeReservoir> getVoiture(){
        List<TypeReservoir> t = typeReservoirRepository.findAll();
        if(t.isEmpty())
            throw new IllegalStateException("Aucun type trouvée");
        return t;
    }

    public String deleteType(String id){
        TypeReservoir t = typeReservoirRepository.findById(id).orElseThrow(()-> new IllegalStateException("Not found"));

        typeReservoirRepository.delete(t);
        return "Supprimé avec succès";
    }
}
