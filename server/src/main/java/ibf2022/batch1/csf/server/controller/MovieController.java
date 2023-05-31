package ibf2022.batch1.csf.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.batch1.csf.server.model.Review;
import ibf2022.batch1.csf.server.service.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@Controller
@RequestMapping(path = "/api")
@CrossOrigin(origins = "*")
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @GetMapping(path = "/search")
    public ResponseEntity<String> searchReview(String query) {
        List<Review> reviews = movieService.searchReview(query);
        JsonArrayBuilder jab = Json.createArrayBuilder();
        try {
            for (Review r : reviews) {
                int reviewCount = movieService.countComments(r.getDisplayTitle());
                r.setReviewCount(reviewCount);
                JsonObject jo = r.toJson();
                jab.add(jo);
            }
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jab.build().toString());
        } catch (Exception e) {
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(Json.createObjectBuilder()
            .add("error", e.getMessage()).build().toString());
        }
    }

    @PostMapping(path = "/comment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public void submitComment(@RequestPart String name, @RequestPart String rating, @RequestPart String comment, @RequestPart String title) {
        movieService.submitComment(name, rating, comment, title);
    }
}
