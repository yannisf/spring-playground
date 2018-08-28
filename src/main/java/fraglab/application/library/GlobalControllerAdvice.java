package fraglab.application.library;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class})
public class GlobalControllerAdvice {

    @ExceptionHandler(InvalidModelException.class)
    public ResponseEntity<ResponseError> invalidModel(InvalidModelException exception) {
        ResponseError responseError = new ResponseError("Model validation error", exception.getFieldErrors());
        return ResponseEntity.badRequest().body(responseError);
    }

} 