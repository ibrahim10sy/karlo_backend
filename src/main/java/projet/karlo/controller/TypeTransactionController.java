package projet.karlo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import projet.karlo.model.TypeTransaction;
import projet.karlo.service.TypeTransactionService;

@RestController
@RequestMapping("/typeTransaction")
public class TypeTransactionController {

    @Autowired
    TypeTransactionService typeTransactionService;

    @PostMapping("/addTypeTransaction")
    @Operation(summary="Création d'un type de transaction")
    public ResponseEntity<TypeTransaction> createTypes(@RequestBody TypeTransaction ypeTransaction) {
        return new ResponseEntity<>(typeTransactionService.createType(ypeTransaction) , HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary="Modification")
    public ResponseEntity<TypeTransaction> updateTypeTransaction(@PathVariable String id, @RequestBody TypeTransaction typeTransaction) {
        return new ResponseEntity<>(typeTransactionService.updateType(typeTransaction, id), HttpStatus.OK);
    }

    @GetMapping("/getAllTypeTransaction")
    @Operation(summary="Liste de tout les types de transaction")
    public ResponseEntity<List<TypeTransaction>> getAll(){
        return new ResponseEntity<>(typeTransactionService.getAllTypes(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary="Supprimé de TypeTransaction")
    public String deleteTypeTransactions(@PathVariable String id) {
        return typeTransactionService.deleteType(id);
    }
}
