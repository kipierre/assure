package org.cephas.acdia.advice;


import org.cephas.acdia.exception.BadRequestException;
import org.cephas.acdia.exception.ResourceAlreadyInUseException;
import org.cephas.acdia.exception.ResourceNotFoundException;
import org.cephas.acdia.payload.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AuthControllerAdvice {

	//private static final Logger logger = Logger.getLogger(AuthControllerAdvice.class);

	@Autowired
	private MessageSource messageSource;


	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponse processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<ObjectError> allErrors = result.getAllErrors();
		ApiResponse response = new ApiResponse();
		response.setSuccess(false);
		response.setData(processAllErrors(allErrors).stream().collect(Collectors.joining("\n")));
		return response;
	}

	/**
	 * Utility Method to generate localized message for a list of field errors
	 * @param allErrors the field errors
	 * @return the list
	 */
	private List<String> processAllErrors(List<ObjectError> allErrors) {
		return allErrors.stream().map(this::resolveLocalizedErrorMessage).collect(Collectors.toList());
	}

	/**
	 * Resolve localized error message. Utiity method to generate a localized error
	 * message
	 * @param objectError the field error
	 * @return the string
	 */
	private String resolveLocalizedErrorMessage(ObjectError objectError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);
		//logger.info(localizedErrorMessage);
		return localizedErrorMessage;
	}



	@ExceptionHandler(value = ResourceAlreadyInUseException.class)
	@ResponseStatus(HttpStatus.IM_USED)
	@ResponseBody
	public ApiResponse handleResourceAlreadyInUseException(ResourceAlreadyInUseException ex) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setSuccess(false);
		apiResponse.setData(ex.getMessage());
		return apiResponse;
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setSuccess(false);
		apiResponse.setData(ex.getMessage());
		return apiResponse;
	}

	@ExceptionHandler(value = BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponse handleBadRequestException(BadRequestException ex) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setSuccess(false);
		apiResponse.setData(ex.getMessage());
		return apiResponse;
	}



}
