package fraglab.application.it.rest;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthRestApiTest {

    private static final String BASE_URL = "http://localhost:8888/playground/";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testPost() {
        List<String> initialCookies = getInitialCookies(BASE_URL);
        String authenticatedSessionIdCookie = getAuthenticationCookie(BASE_URL + "login", "admin", "admin", initialCookies);
        HttpHeaders requestHeaders = getHttpHeadersForAuthenticatedRequests(initialCookies, authenticatedSessionIdCookie);

        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
        ResponseEntity<String> response;

        response = restTemplate.exchange(BASE_URL + "secure/index.jsp", HttpMethod.GET, requestEntity, String.class);
        assertThat(response.getBody()).containsIgnoringCase("secure");

        response = restTemplate.exchange(BASE_URL + "api/secure/author", HttpMethod.GET, requestEntity, String.class);
        assertThat(response.getBody()).containsIgnoringCase("GET");

        response = restTemplate.exchange(BASE_URL + "api/secure/author", HttpMethod.POST, requestEntity, String.class);
        assertThat(response.getBody()).containsIgnoringCase("POST");
    }

    private List<String> getInitialCookies(String baseUrl) {
        return restTemplate.getForEntity(baseUrl, String.class).getHeaders().get(HttpHeaders.SET_COOKIE);
    }

    private String getAuthenticationCookie(String loginUrl, String username, String password, List<String> cookies) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        cookies.forEach(c -> headers.add(HttpHeaders.COOKIE, c));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);
        map.add("password", password);
        map.add("_csrf", getCsrfToken(cookies));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(loginUrl, request, String.class);
        return response.getHeaders().get(HttpHeaders.SET_COOKIE).stream()
                .filter(c -> c.contains("JSESSIONID"))
                .map(c -> c.split(";")[0])
                .findFirst().orElseThrow(IllegalStateException::new);
    }

    private HttpHeaders getHttpHeadersForAuthenticatedRequests(List<String> initialCookies, String authenticatedSessionIdCookie) {
        HttpHeaders requestHeaders = new HttpHeaders();
        String csrfToken = getCsrfToken(initialCookies);

        requestHeaders.add(HttpHeaders.COOKIE, authenticatedSessionIdCookie);
        requestHeaders.add(HttpHeaders.COOKIE, String.format("XSRF-TOKEN=%s", csrfToken));
        requestHeaders.add("X-XSRF-TOKEN", csrfToken);
        return requestHeaders;
    }

    private String getCsrfToken(List<String> cookies) {
        return cookies.stream()
                .filter(c -> c.contains("XSRF"))
                .map(c -> c.split("[=;]")[1])
                .findFirst().orElseThrow(IllegalStateException::new);
    }

}
