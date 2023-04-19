package com.example.demo;

import com.example.demo.dto.ContactDto;
import com.example.demo.entity.Contact;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.NotFoundExceptionCustom;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
import com.example.demo.validator.ValidatorContact;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootRetfulApplicationTests {

    @Autowired
    private ContactService contactService;

    @MockBean
    private ContactRepository contactRepository;

    @Autowired
    private ValidatorContact validatorContact;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BEFORE ALL");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("AFTER ALL");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("BEFORE EACH");
    }

    @AfterEach
    public void AfterEach() {
        System.out.println("AFTER EACH");
    }

    @Test
    public void addContactTest() throws BusinessException {
        ModelMapper modelMapper = new ModelMapper();

        ContactDto contactDto = new ContactDto(999L, "quang", "nien",
                "quangnien24@gmail.com", "0395624358", "Bình Thạnh, TP HCM");
        Contact contactEntity = modelMapper.map(contactDto, Contact.class);

        // Validate the contactDto using a validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(contactDto);
        assertTrue(violations.isEmpty());

        // Validator manual
        validatorContact.validateAddContact(contactDto);

        when(contactRepository.save(contactEntity)).thenReturn(contactEntity);

        Contact resultDto = contactService.addContact(contactDto);
        Contact resultEntity = modelMapper.map(resultDto, Contact.class);

        assertEquals(contactEntity, resultEntity);
    }

    @Test
    public void testUpdateContact() throws BusinessException {
        // given
        Long id = 1L;
        ModelMapper modelMapper = new ModelMapper();
        Contact existingContact = new Contact(id, "quang", "nien ", "quangnien24@gmail.com", "0395624358", "Bình Thạnh, TP HCM");
        ContactDto updatedContact = new ContactDto(id, "nguyen", "nien", "quangnien24@gmail.com", "0395624358", "Bình Thạnh, TP HCM");
        Contact updatedContactEntity = modelMapper.map(updatedContact, Contact.class);

        given(contactRepository.findById(id)).willReturn(Optional.of(existingContact));
        given(contactRepository.save(updatedContactEntity)).willReturn(updatedContactEntity);

        // Validate the contactDto using a validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(updatedContact);
        assertTrue(violations.isEmpty());

        // Validator manual
        validatorContact.validateAddContact(updatedContact);

        // when
        Contact result = contactService.updateContact(updatedContact, id);

        // then
        assertNotNull(result);
        assertEquals("nguyen", result.getFirstName());
        assertEquals("nien", result.getLastName());
        assertEquals("quangnien24@gmail.com", result.getEmail());
        assertEquals("0395624358", result.getPhoneNumber());
        assertEquals("Bình Thạnh, TP HCM", result.getPostalAddress());
        verify(contactRepository, times(1)).save(updatedContactEntity);
    }

    @Test
    public void getContactByIdTest(){
        Long id = 1L;
        Contact contact = new Contact(id, "quang", "nien", "quangnien24@gmail.com", "0395624358", "Bình Thạnh, TP HCM");

        given(contactRepository.findById(id)).willReturn(Optional.of(contact));

        Contact result = contactService.findById(id);

        assertNotNull(result);
        assertEquals("quang", result.getFirstName());
    }

    @Test
    public void testDeleteContact() {
        ModelMapper modelMapper = new ModelMapper();

        ContactDto contactDto = new ContactDto(999L, "quang", "nien",
                "quangnien24@gmail.com", "0395624358", "Bình Thạnh, TP HCM");
        Contact contactEntity = modelMapper.map(contactDto, Contact.class);

        given(contactRepository.findById(999L)).willReturn(Optional.of(contactEntity));
        doNothing().when(contactRepository).deleteById(999L);

        Contact contactCall = contactService.findById(999L);

        // Act & Assert
        assertThrows(NotFoundExceptionCustom.class, () -> contactService.deleteContactById(999L));
    }

    @Test
    public void testGetAllContacts() {
        ModelMapper modelMapper = new ModelMapper();

        List<Contact> contactList = new ArrayList<>();
        contactList.add(new Contact(1L, "quang", "nien", "quangnien24@gmail.com", "0395624358", "Bình Thạnh, TP HCM"));
        contactList.add(new Contact(2L, "thu", "nguyen", "thunguyen@gmail.com", "0987654321", "Tân Bình, TP HCM"));

        when(contactRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(contactList));

        List<Contact> result = contactService.getAllContacts(0, 10);

        assertEquals(result.size(), contactList.size());
        assertEquals(result.get(0).getFirstName(), contactList.get(0).getFirstName());
        assertEquals(result.get(1).getFirstName(), contactList.get(1).getFirstName());
    }

    @Test
    public void testSearchContacts() {
        // Arrange
        ModelMapper modelMapper = new ModelMapper();

        ContactDto contactDto1 = new ContactDto(1L, "quang", "nien", "quangnien24@gmail.com", "0395624358", "Bình Thạnh, TP HCM");
        ContactDto contactDto2 = new ContactDto(2L, "thu", "nguyen", "thunguyen@gmail.com", "0987654321", "Tân Bình, TP HCM");
        List<ContactDto> contactDtoList = Arrays.asList(contactDto1, contactDto2);
        List<Contact> contactEntityList = contactDtoList.stream().map(dto -> modelMapper.map(dto, Contact.class)).collect(Collectors.toList());

        when(contactRepository.findByFirstNameContainingIgnoreCase("quang")).thenReturn(contactEntityList.stream().filter(c -> c.getFirstName().equalsIgnoreCase("quang")).collect(Collectors.toList()));
        when(contactRepository.findByLastNameContainingIgnoreCase("nien")).thenReturn(contactEntityList.stream().filter(c -> c.getLastName().equalsIgnoreCase("nien")).collect(Collectors.toList()));

        // Act
        List<Contact> searchResult1 = contactService.searchContacts("quang", null);
        List<Contact> searchResult2 = contactService.searchContacts(null, "nien");
        List<Contact> searchResult3 = contactService.searchContacts("quang", "nien");

        // Assert
        assertEquals(searchResult1.size(), 1);
        assertEquals(searchResult1.size(), 1);
        assertEquals(searchResult1.size(), 1);
    }
}
