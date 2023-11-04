package exercise.controller;

import exercise.dto.ContactCreateDTO;
import exercise.dto.ContactDTO;
import exercise.model.Contact;
import exercise.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    private Contact toEntity(ContactCreateDTO data) {
        var contact = new Contact();
        contact.setFirstName(data.getFirstName());
        contact.setLastName(data.getLastName());
        contact.setPhone(data.getPhone());

        return contact;
    }

    private ContactDTO toDTO(Contact data) {
        var contact = new ContactDTO();
        contact.setId(data.getId());
        contact.setFirstName(data.getFirstName());
        contact.setLastName(data.getLastName());
        contact.setPhone(data.getPhone());
        contact.setCreatedAt(data.getCreatedAt());
        contact.setUpdatedAt(data.getUpdatedAt());

        return contact;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ContactDTO create(@RequestBody ContactCreateDTO data) {
        var contactEntity = toEntity(data);
        contactRepository.save(contactEntity);

        return this.toDTO(contactEntity);
    }
}
