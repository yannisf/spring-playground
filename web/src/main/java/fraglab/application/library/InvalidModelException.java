package fraglab.application.library;

import java.util.Collections;
import java.util.List;

public class InvalidModelException extends RuntimeException {

    private List<String> fieldErrors;

    public InvalidModelException(List<String> fieldErrors) {
        super(fieldErrors.get(0));
        this.fieldErrors = fieldErrors;
    }

    public List<String> getFieldErrors() {
        return Collections.unmodifiableList(fieldErrors);
    }

}
