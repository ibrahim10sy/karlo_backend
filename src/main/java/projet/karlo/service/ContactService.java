package projet.karlo.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.karlo.model.Alerte;
import projet.karlo.model.Contact;
import projet.karlo.model.Role;
import projet.karlo.repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    IdGenerator idGenerator;
    @Autowired
    EmailService emailService;

    public Contact createContact(Contact contact) {
        String idcodes = idGenerator.genererCode();
        contact.setIdContact(idcodes);
        contact.setDateAjout(LocalDateTime.now().toString());
        Alerte al = new Alerte(contact.getEmail(), contact.getMessage());
        emailService.sendSimpleMail(al);

        return contactRepository.save(contact);
    }

    public Contact updateContact(Contact contact, String id){
        Contact c = contactRepository.findById(id).orElseThrow(() -> new IllegalStateException("Couldn't find contact"));
        c.setNomComplet(contact.getNomComplet());
        c.setTelephone(contact.getTelephone());
        c.setEmail(contact.getEmail());
        c.setMessage(contact.getMessage());

        return contactRepository.save(c);
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = contactRepository.findAll();

        if(contacts.isEmpty())
            throw new IllegalStateException("No contacts");

        contacts.sort(Comparator.comparing(Contact::getDateAjout));

        return contacts;
    }

    public String deleteContact(String id){
        Contact contact = contactRepository.findById(id).orElseThrow(()-> new IllegalStateException("Couldn't find contact"));

        contactRepository.delete(contact);
        return "Supprimé avec succèss";
    }
}
