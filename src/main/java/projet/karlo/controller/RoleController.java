package projet.karlo.controller;

import java.util.List;

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

import io.swagger.v3.oas.annotations.Operation;
import projet.karlo.model.Role;
import projet.karlo.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/addRole")
    @Operation(summary="Création de rôle d'uilisateur")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        System.out.println(role.toString());
        return new ResponseEntity<>(roleService.createRole(role) , HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary="Modification")
    public ResponseEntity<Role> updateRole(@PathVariable String id, @RequestBody Role role) {
        return new ResponseEntity<>(roleService.updateRole(role, id), HttpStatus.OK);
    }

    @GetMapping("/getAllRole")
    @Operation(summary="Liste de tout les rôles")
    public ResponseEntity<List<Role>> getAll(){
        return new ResponseEntity<>(roleService.getAllRole(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary="Supprimé de Role")
    public String deleteRoles(@PathVariable String id) {
        return roleService.deleteRole(id);
    }
}
