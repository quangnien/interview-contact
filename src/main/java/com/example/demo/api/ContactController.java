package com.example.demo.api;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.dto.ContactDto;
import com.example.demo.validator.ValidatorContact;
import com.example.demo.common.ReturnObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Contact;
import com.example.demo.ContactService;

@Slf4j
@RestController
@RequestMapping("/api/contact")
@Tag(name = "Contact", description = "Management APIs for CONTACTS.")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ValidatorContact validatorContact;

    /* CREATE */
    @Operation(summary = "Create Contact.")
    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) })})
    public ResponseEntity<?> createContact(@Valid @RequestBody ContactDto contactDto, BindingResult bindingResult) {

        ReturnObject returnObject = new ReturnObject();

        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Add Contact!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            /* VALIDATOR : AOP -> break module */
            validatorContact.validateAddContact(contactDto);

            Contact contactEntity =  contactService.addContact(contactDto);

            returnObject.setRetObj(contactEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* UPDATE */
    @PutMapping("/{id}")
    @Operation(summary = "Update Contact.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) })})
    public ResponseEntity<?>  updateContact(@Valid @RequestBody ContactDto contactDto, @PathVariable Long id, BindingResult bindingResult) {
        
        ReturnObject returnObject = new ReturnObject();
        if (bindingResult.hasErrors()) {
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return ResponseEntity.ok(returnObject);
        }
        try {
            log.info("Update Contact!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            /* VALIDATOR : AOP -> break module */
            validatorContact.validateEditContact(contactDto, id);

            Contact contactEntity = contactService.updateContact(contactDto, id);

            returnObject.setRetObj(contactEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    /* GET BY ID*/
    @GetMapping("/{id}")
    @Operation(summary = "Get Contact By Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) })})
    public ResponseEntity<?> getContactById(@PathVariable Long id) {
        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get Contact By Id!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            /* VALIDATOR : AOP -> break module */
            validatorContact.validateGetContactById(id);

            Contact contactEntity = contactService.findById(id);

            returnObject.setRetObj(contactEntity);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Contact By Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) })})
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Delete Contact!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            /* VALIDATOR : AOP -> break module */
            validatorContact.validateDeleteContactById(id);

            contactService.deleteContactById(id);
            returnObject.setRetObj(id);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    @GetMapping
    @Operation(summary = "Get All Contact.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) })})
    public ResponseEntity<?> getAllContacts(@RequestParam(defaultValue = "0") int page,
    		@RequestParam(defaultValue = "10") int size) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All Contact!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<Contact> listContact = contactService.getAllContacts(page, size);
            returnObject.setRetObj(listContact);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

    @GetMapping("/search")
    @Operation(summary = "Search Contact.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class)) })})
    public ResponseEntity<?> searchContacts(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {

        ReturnObject returnObject = new ReturnObject();
        try {
            log.info("Get All Contact!");

            returnObject.setStatus(ReturnObject.SUCCESS);
            returnObject.setMessage("200");

            List<Contact> listContact = contactService.searchContacts(firstName, lastName);
            returnObject.setRetObj(listContact);
        }
        catch (Exception ex){
            returnObject.setStatus(ReturnObject.ERROR);
            returnObject.setMessage(ex.getMessage());
        }

        return ResponseEntity.ok(returnObject);
    }

}
