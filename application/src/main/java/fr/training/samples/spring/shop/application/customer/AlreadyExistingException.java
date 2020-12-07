package fr.training.samples.spring.shop.application.customer;

import fr.training.samples.spring.shop.domain.common.exception.BusinessException;

public class AlreadyExistingException extends BusinessException {
    public AlreadyExistingException(String message) {
        super(message);
    }
}
