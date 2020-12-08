package fr.training.samples.spring.shop.exposition.common;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import fr.training.samples.spring.shop.domain.common.exception.AlreadyExistingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ExceptionTranslator.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		final String errors = ex.getBindingResult() //
				.getFieldErrors().stream() //
				.map(x -> x.getField() + ": " + x.getDefaultMessage()) //
				.collect(Collectors.joining(","));

		final ErrorModel apiError = ErrorModel.builder() //
				.code("") //
				.message(errors) //
				.description("Please check your parameters")//
				.build();

		//LOG.info(ex.getMessage()); pas nécessaire de loguer ça, à l'utilisateur de corriger sa requete
		LOG.debug(ex.getMessage());
		return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraint(final ConstraintViolationException ex) {

		final ErrorModel apiError = ErrorModel.builder() //
				.code("") //
				.message(ex.getLocalizedMessage()) //
				.description("error occurred")//
				.build();

		//LOG.info(ex.getMessage()); pas nécessaire de loguer ça, à l'utilisateur de corriger sa requete
		LOG.debug(ex.getMessage());

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	//--- au dessus de cette limite : commun à tout projet
	//-- en dessous : spécifique projet
	@ExceptionHandler(value = { NotFoundException.class })
	@ResponseBody
	public ResponseEntity<Object> handleNotFoundException(final NotFoundException ex) {

		final ErrorModel apiError = ErrorModel.builder() //
				.message(ex.getLocalizedMessage()) //
				.description("The given identifier is unknown by the system.")//
				.build();

		LOG.info(ex.getMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	//eventuellement dans value on peut mettre plusieurs class dans les crochets si plusieurs
	// exceptions doivent etre traitées de la meme manière
	@ExceptionHandler(value = { AlreadyExistingException.class })
	@ResponseBody
	public ResponseEntity<Object> handleAlreadyExistingException(final AlreadyExistingException ex) {

		final ErrorModel apiError = ErrorModel.builder() //
				.message(ex.getLocalizedMessage()) //
				.description("A customer with same name already exists.")//
				.build();

		LOG.info(ex.getMessage());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ResponseEntity<Object> handleOthers(final Exception ex) {

		final ErrorModel apiError = ErrorModel.builder() //
				.code("err.internal") //
				.message(ex.getLocalizedMessage()) //
				.description("internal error occurred")//
				.build();

		LOG.error(ex.getMessage(), ex);

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}