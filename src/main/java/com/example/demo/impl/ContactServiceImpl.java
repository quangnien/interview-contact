package com.example.demo.impl;

import java.util.Collections;
import java.util.List;

import com.example.demo.ContactService;
import com.example.demo.dto.ContactDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.exception.NotFoundExceptionCustom;
import com.example.demo.repository.ContactRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return contactRepository.findAll(pageable).getContent();
    }

    @Override
    public Contact findById(Long id) {
        Contact contactEntity = contactRepository.findById(id).get();
        if (contactEntity != null) {
            return contactEntity;
        }
        else {
            throw new NotFoundExceptionCustom("Contact not found with id " + id);
        }
    }

    @Override
    public void deleteContactById(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new NotFoundExceptionCustom("Contact not found with id " + id);
        }
        contactRepository.deleteById(id);
    }

    @Override
    public Contact updateContact(ContactDto updatedContact, Long id) {
        Contact contactEntity = contactRepository.findById(id).get();
        if (contactEntity != null) {
            contactEntity.setFirstName(updatedContact.getFirstName());
            contactEntity.setLastName(updatedContact.getLastName());
            contactEntity.setEmail(updatedContact.getEmail());
            contactEntity.setPhoneNumber(updatedContact.getPhoneNumber());
            contactEntity.setPostalAddress(updatedContact.getPostalAddress());
            return contactRepository.save(contactEntity);
        } else {
            throw new NotFoundExceptionCustom("Contact not found with id " + id);
        }
    }

    @Override
    public List<Contact> searchContacts(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return contactRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
        } else if (firstName != null) {
            return contactRepository.findByFirstNameContainingIgnoreCase(firstName);
        } else if (lastName != null) {
            return contactRepository.findByLastNameContainingIgnoreCase(lastName);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Contact addContact(ContactDto contactDto) {
        ModelMapper modelMapper = new ModelMapper();
        Contact contact = modelMapper.map(contactDto, Contact.class);
        contactRepository.save(contact);
        return contact;
    }

    @Override
    public int countByEmail(String email) {
        return contactRepository.countByEmail(email);
    }

    @Override
    public int countByPhoneNumber(String phoneNumber) {
        return contactRepository.countByPhoneNumber(phoneNumber);
    }

    @Override
    public int countByEmailAndIdNot(String email, Long id) {
        return contactRepository.countByEmailAndIdNot(email, id);
    }

    @Override
    public int countByPhoneNumberAndIdNot(String phoneNumber, Long id) {
        return contactRepository.countByPhoneNumberAndIdNot(phoneNumber, id);
    }


}
