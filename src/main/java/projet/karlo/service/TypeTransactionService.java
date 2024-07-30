package projet.karlo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.karlo.model.TypeReservoir;
import projet.karlo.model.TypeTransaction;
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


}
