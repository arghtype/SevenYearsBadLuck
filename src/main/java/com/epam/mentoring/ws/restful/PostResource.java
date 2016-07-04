package com.epam.mentoring.ws.restful;

import org.jboss.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Maksim_Yashin@epam.com
 */
@Path("likes")
public class PostResource {
    private static final Map<Long, Post> posts = new ConcurrentHashMap<>();
    private static final Logger LOGGER = Logger.getLogger(PostResource.class);

    static{
        posts.put(1l, new Post("Post from static creation in PostResource", 1l));
    }

    public PostResource() {
        LOGGER.info("created PostResource");
    }

    @POST
    @Path("/add/{text}")
    public void addPost(@PathParam("text")String text) {
        LOGGER.info("addPost : "+ text);
        Long newId = generateNewId();
        posts.put(newId, new Post(text, newId));
    }

    @DELETE
    @Path("/{id}")
    public void deletePost(@PathParam("id")Long id) {
        LOGGER.info("deletePost : " + id);
        posts.remove(id);
    }

    @PUT
    @Path("/like/{id}")
    public void likePost(@PathParam("id")Long id) {
        LOGGER.info("likePost : " + id);
        final Post post = posts.get(id);
        if (post != null) {
            post.like();
        }
    }

    @PUT
    @Path("/unlike/{id}")
    public void unlikePost(@PathParam("id")Long id) {
        LOGGER.info("unlikePost : " + id);
        final Post post = posts.get(id);
        if (post != null) {
            post.dislike();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getAll() {
        LOGGER.info("getAll");
        ArrayList<Post> result = new ArrayList<>(posts.values());
        return result ;

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post getPost(@PathParam("id")Long id) {
        LOGGER.info("get: " + id);
        return posts.get(id);
    }

    private long generateNewId(){
        Long max = 0L;
        for(Long key : posts.keySet()){
            if (key > max) {
                max = key;
            }
        }
        Long newKey = max + 1;
        return !posts.keySet().contains(newKey) ? newKey : generateNewId();
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
            if(likes > 0){
                likes--;
            }
        }
    }
}
