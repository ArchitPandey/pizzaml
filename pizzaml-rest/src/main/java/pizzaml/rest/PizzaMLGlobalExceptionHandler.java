package pizzaml.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PizzaMLGlobalExceptionHandler {

	@ExceptionHandler({Exception.class}) 
	public ResponseEntity<PizzaMLErrorResponse> handleException(Exception e) {
		
		PizzaMLErrorResponse error = 
				new PizzaMLErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage());
		
		return new ResponseEntity<PizzaMLErrorResponse>(error,HttpStatus.BAD_REQUEST);
	}
}
