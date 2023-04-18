//package com.example.demo.UnitTests;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.example.demo.service.ContactService;
//
////@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
////@ActiveProfiles("test")
//public class ContactServiceTest {
//
//    @Autowired
//    private ContactService contactService;
//
////    private ContactService contactService;
////
////    @Mock
////    private ContactRepository contactRepository;
//
//
//
////    @Test
////    public void testGetAllContacts() {
////        List<Contact> contacts = contactService.getAllContacts(0, 10);
////        assertNotNull(contacts);
////    }
//
////    @Test
////    public void testGetContactById() {
////        Contact contact = contactService.getContactById(1L);
////        assertNotNull(contact);
////    }
////
////    @Test
////    public void testDeleteContact() {
////        contactService.deleteContact(1L);
////        assertNull(contactService.getContactById(1L));
////    }
////
////    @Test
////    public void testUpdateContact() {
////        Contact contact = contactService.findById(1L);
////        contact.setFirstName("UpdatedFirstName");
////        contactService.updateContact(contact);
////        assertEquals("UpdatedFirstName", contactService.getContactById(1L).getFirstName());
////    }
////
////    @Test
////    public void testSearchContacts() {
////        List<Contact> contacts = contactService.searchContacts("John", "Doe");
////        assertNotNull(contacts);
////    }
//
//}
