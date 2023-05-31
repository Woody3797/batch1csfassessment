package ibf2022.batch1.csf.server.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {
    
    @Autowired
    MongoTemplate mongoTemplate;

    // db.comments.countDocuments(
    //     { display_title: title}
    // )
    public Integer countComments(String title) {
        Query query = Query.query(Criteria.where("display_title").is(title));
        List<Document> docs = mongoTemplate.find(query, Document.class, "comments");
        
        return docs.size();
    }


    public void submitComment(String name, String rating, String comment, String title) {
        Query query = Query.query(Criteria.where("display_title").is(title).andOperator(Criteria.where("name").is(name)));
        Update update = new Update()
        .set("rating", rating)
        .set("comment", comment);

        mongoTemplate.upsert(query, update, Document.class, "comments");
    }
}
