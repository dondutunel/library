
package com.dondu.myprisma;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class LibraryResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
	
	
	LibraryRepository repo = new LibraryRepository();
	
    
    @GET 
    @Path("/getActiveUsers")
    @Produces({ MediaType.APPLICATION_XML })
    public List<User> getActiveUsers() throws Exception{
    	return repo.getActiveUsers();
    }
    
    
    @GET 
    @Path("/getDeactiveUsers")
    @Produces({ MediaType.APPLICATION_XML })
    public List<User> getDeactiveUsers() throws Exception{
    	return repo.getDeActiveUsers();
    }
}
