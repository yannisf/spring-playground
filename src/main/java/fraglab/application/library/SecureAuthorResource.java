package fraglab.application.library;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/author")
public class SecureAuthorResource {

    @GetMapping
    public String get() {
        return "GET request";
    }

    @PostMapping
    public String post() {
        return "POST request";
    }

}
