package plants.controller.error;

import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

//used to handle exceptions globally in the application
@RestControllerAdvice
//lombok annotation to automatically generate a log for the class
@Slf4j
public class GlobalErrorHandler {

	//used to define a method that handles specific exceptions
	@ExceptionHandler(NoSuchElementException.class)
	//will return 404 status when NSEE is thrown
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, String> handleNoSuchElementException(NoSuchElementException ex) {
		//logs exception with a custom message to help with debugging
		log.error("Exception: {}", ex.toString());
		
		//returns custom message in response to exception details
		return Map.of("message", ex.toString());
	}
}