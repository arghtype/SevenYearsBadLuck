package com.epam.mentoring.ws.restful;

import org.jboss.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * @author Maksim_Yashin@epam.com
 */
@Path("likes")
public class PostResource {
    private static final Map<Long, Post> posts = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(PostResource.class);

    public PostResource() {
        LOGGER.info("created PostResource");
        posts.put(1l, new Post("TEXT1", 1l));
    }

    @PUT
    @Path("/add/{id}/{text}")
    public void addPost(@PathParam("id")Long id, @PathParam("text")String text) {
        LOGGER.info("addPost : " + id + ", " + text);
        posts.put(id, new Post(text, id));
    }

    @DELETE
    @Path("/delete/{id}")
    public void deletePost(@PathParam("id")Long id) {
        LOGGER.info("deletePost : " + id);
        posts.remove(id);
    }

    @POST
    @Path("/like/{id}")
    public void likePost(@PathParam("id")Long id) {
        LOGGER.info("likePost : " + id);
        final Post post = posts.get(id);
        if (post != null) {
            post.like();
        }
    }

    @POST
    @Path("/dislike/{id}")
    public void dislikePost(@PathParam("id")Long id) {
        LOGGER.info("dislikePost : " + id);
        final Post post = posts.get(id);
        if (post != null) {
            post.dislike();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getAll() {
        LOGGER.info("getAll");
        ArrayList<Post> result = new ArrayList<>();
        result.addAll(posts.values());
        return result ;

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post getPost(@PathParam("id")Long id) {
        LOGGER.info("get: " + id);
        return posts.get(id);
    }

    @XmlRootElement
    private static class Post {
        public String text;
        public int likes;
        public long id;

        public Post(){}

        Post(String text, Long id) {
            this.text = text;
            this.likes = 0;
            this.id = id;
        }

        void like() {
            likes++;
        }

        void dislike() {
            likes--;
        }
    }
}
