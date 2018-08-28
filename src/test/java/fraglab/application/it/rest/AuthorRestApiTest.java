package fraglab.application.it.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fraglab.application.it.AbstractRestIntegrationTest;
import fraglab.application.library.Author;
import fraglab.application.library.ResponseError;
import org.springframework.http.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorRestApiTest extends AbstractRestIntegrationTest {

    private static final String RESOURCE_URL = "http://localhost:8080/api/author";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void testPost() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Author author = new Author("Yannis");
        String jsonAuthor = MAPPER.writeValueAsString(author);
        HttpEntity<String> entity = new HttpEntity<>(jsonAuthor, headers);
        ResponseEntity<Author> exchange = restTemplate.exchange(RESOURCE_URL, HttpMethod.POST, entity, Author.class);

        Author postAuthor = exchange.getBody();
        Long id = postAuthor.getId();
        Author getAuthor = restTemplate.getForObject(String.format("%s/%s", RESOURCE_URL, id), Author.class);
        assertThat(getAuthor.getName()).isEqualTo("Yannis");

        author = new Author("YannisX");
        jsonAuthor = MAPPER.writeValueAsString(author);
        entity = new HttpEntity<>(jsonAuthor, headers);
        ResponseEntity<ResponseError> errorEntity = restTemplate.exchange(RESOURCE_URL, HttpMethod.POST, entity, ResponseError.class);
        assertThat(errorEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errorEntity.getBody().getMessages()).hasSize(1);
    }

}
