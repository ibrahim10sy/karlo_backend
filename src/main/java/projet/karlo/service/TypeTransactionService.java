package projet.karlo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.karlo.model.TypeReservoir;
import projet.karlo.model.TypeTransaction;
import projet.karlo.model.VoitureLouer;
import projet.karlo.repository.TypeTransactionRepository;

@Service
public class TypeTransactionService {

    @Autowired
    TypeTransactionRepository typeRepository;
    @Autowired
    IdGenerator idGenerator ;

    public TypeTransaction createType(TypeTransaction type){
        TypeTransaction r = typeRepository.findByLibelle(type.getLibelle());

        if(r != null)
            throw new IllegalStateException("Ce type existe déjà");

        String idcodes = idGenerator.genererCode();
        type.setIdTypeTransaction(idcodes);

        return typeRepository.save(type);
    }

    public TypeTransaction updateType(TypeTransaction type,String id){
        TypeTransaction t = typeRepository.findById(id).orElseThrow(()-> new IllegalStateException("Not found"));

        t.setLibelle(type.getLibelle());

        return typeRepository.save(t);
    }

    public List<TypeTransaction> getAllTypes() {
        List<TypeTransaction> typeList =  typeRepository.findAll();
        if(typeList.isEmpty())
            throw new IllegalStateException("Aucune transaction trouvée");
    return typeList;

    }

    public String deleteType(String id){
        TypeTransaction type = typeRepository.findById(id).orElseThrow();
        typeRepository.delete(type);
        return "Supprimé avec succès";
    }
}
