/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdcm.rest.resources;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import model.Video;

/**
 *
 * @author alumne
 */

// RESTful/RESTful/...
@Path("RESTful")
public class RESTful {
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getVideos(@QueryParam("searchval") String searchVal){
        Video video = new Video();
        String message = video.searchDB(searchVal);
        return message;
    }
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String putVideos(@QueryParam("videoid") String videoid){
        Video video = new Video();
        String message = video.upOneRepro(videoid);
        return message;
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String postVideos(@QueryParam("videotitle") String videotitle,@QueryParam("authorid") String authorid ){
        Video video = new Video();
        String message = video.returnVideoId(videotitle, authorid);
        return message;
    }
    
    

}

