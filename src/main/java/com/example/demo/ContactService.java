package com.example.demo;

import java.util.List;

import com.example.demo.dto.ContactDto;
import com.example.demo.entity.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactService {
    List<Contact> getAllContacts(int page, int size);
    List<Contact> searchContacts(String firstName, String lastName);

    Contact addContact(ContactDto contactDto);
    Contact updateContact(ContactDto updatedContact, Long id);
    Contact findById(Long id);
    void deleteContactById(Long id);


    int countByEmail(String email);
    int countByPhoneNumber(String phoneNumber);

    int countByEmailAndIdNot(String email, Long id);
    int countByPhoneNumberAndIdNot(String phoneNumber, Long id);

}
