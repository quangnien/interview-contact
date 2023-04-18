package com.example.demo.validator;

import com.example.demo.constant.MasterDataExceptionConstant;
import com.example.demo.dto.ContactDto;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidatorContact implements Validator {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private FunctionCommon functionCommon;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Transactional
    public void validateAddContact(Object target) throws BusinessException {

        ContactDto contactDto = (ContactDto) target;

        int countEmail = contactRepository.countByEmail(contactDto.getEmail());
        int countPhoneNumber = contactRepository.countByPhoneNumber(contactDto.getPhoneNumber());

        if(functionCommon.isValidEmailFormat(contactDto.getEmail()) == false){
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_WRONG_FORMAT);
        }
        else if (countEmail > 0) {
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_IS_EXIST);
        }
        else if (countPhoneNumber > 0) {
            throw new BusinessException(MasterDataExceptionConstant.COMMON_PHONE_NUMBER_IS_EXIST);
        }
    }

    @Transactional
    public void validateEditContact(Object target, Long id) throws BusinessException {
        ContactDto contactDto = (ContactDto) target;

        int countById = contactRepository.countById(id);
        int countEmail = contactRepository.countByEmailAndIdNot(contactDto.getEmail(), id);
        int countPhoneNumber = contactRepository.countByPhoneNumberAndIdNot(contactDto.getPhoneNumber(), id);

        if(functionCommon.isValidEmailFormat(contactDto.getEmail()) == false){
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_WRONG_FORMAT);
        }
        else if (countEmail > 0) {
            throw new BusinessException(MasterDataExceptionConstant.COMMON_EMAIL_IS_EXIST);
        }
        else if (countPhoneNumber > 0) {
            throw new BusinessException(MasterDataExceptionConstant.COMMON_PHONE_NUMBER_IS_EXIST);
        }
        else if (countById == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_CONTACT_NOT_FOUND_CONTACT);
        }
    }

    @Transactional
    public void validateGetContactById(Long contactId) throws BusinessException {

        int countContactById = contactRepository.countById(contactId);

        if (countContactById == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_CONTACT_NOT_FOUND_CONTACT);
        }
    }

    @Transactional
    public void validateDeleteContactById(Long contactId) throws BusinessException {

        int countContactById = contactRepository.countById(contactId);

        if (countContactById == 0) {
            throw new BusinessException(MasterDataExceptionConstant.E_CONTACT_NOT_FOUND_CONTACT);
        }
    }
}