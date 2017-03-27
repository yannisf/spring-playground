package fraglab.application.library;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookService {

    @Cacheable(cacheNames = {"local"}, key = "#id")
    public String generate(String id) {
        return String.format("%s:%s", id, UUID.randomUUID().toString());
    }

}
