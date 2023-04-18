package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);

	List<Contact> findByFirstNameContainingIgnoreCase(String firstName);

	List<Contact> findByLastNameContainingIgnoreCase(String lastName);

	int countByEmail(String email);
	int countByPhoneNumber(String phoneNumber);

	int countByEmailAndIdNot(String email,Long id);
	int countByPhoneNumberAndIdNot(String phoneNumber,Long id);

	int countById(Long id);
	Optional<Contact> findById(Long id);
}
