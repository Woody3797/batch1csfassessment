package ibf2022.batch1.csf.server.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.server.model.Review;
import ibf2022.batch1.csf.server.repository.MovieRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    
    static final String MOVIE_API = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";

    public List<Review> searchReview(String query) {
        String movieURL = UriComponentsBuilder.fromUriString(MOVIE_API).queryParam("query", query.replaceAll(" ", "+")).queryParam("api-key", "S2j6bGa7tjsb2wdnyGJvoySVRWUZcOLx")
        .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.getForEntity(movieURL, String.class);
        List<Review> reviews = new ArrayList<>();

        try {
            String payload = resp.getBody();
            JsonReader jr = Json.createReader(new StringReader(payload));
            JsonObject jo = jr.readObject();
            JsonArray jArr = jo.getJsonArray("results");
            for (JsonValue v : jArr) {
                Review r = Review.convertFromJson(v.asJsonObject());
                reviews.add(r);
            }
            return reviews;
        } catch (Exception e) {
            return reviews;
        }
    }

    public Integer countComments(String title) {
        return movieRepository.countComments(title);
    }

    public void submitComment(String name, String rating, String comment, String title) {
        movieRepository.submitComment(name, rating, comment, title);
    }
}
