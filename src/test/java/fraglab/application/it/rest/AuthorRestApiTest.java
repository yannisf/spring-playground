package fraglab.application.it.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fraglab.application.it.AbstractRestIntegrationTest;
import fraglab.application.library.Author;
import org.junit.Test;
import org.springframework.http.*;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AuthorRestApiTest extends AbstractRestIntegrationTest {

    private String resourceUrl = "http://localhost:8080/api/author";

    @Test
    public void testPost() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        Author author = new Author("Yannis");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAuthor = objectMapper.writeValueAsString(author);
        HttpEntity<String> entity = new HttpEntity<>(jsonAuthor, headers);
        ResponseEntity<Author> exchange = restTemplate.exchange(resourceUrl, HttpMethod.POST, entity, Author.class);
        Author postAuthor = exchange.getBody();
        Long id = postAuthor.getId();
        Author getAuthor = restTemplate.getForObject(String.format("%s/%s", resourceUrl, id), Author.class);
        assertThat(getAuthor.getName(), equalTo("Yannis"));
    }

}
