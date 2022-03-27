package myprisma;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.dondu.myprisma.LibraryRepository;

import static io.restassured.RestAssured.get;


import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;


public class LibraryTest {
	
	

	@Test
	void testGetActiveUsers(){
        Response response = get("http://localhost:8080/myprisma/webresources/myresource/getActiveUsers");
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        Assert.assertEquals(statusCode,HttpStatus.SC_NOT_FOUND);
        Assert.assertEquals(statusCode,HttpStatus.SC_BAD_GATEWAY);
    }
	
	
	 @Test
	 public void testFetchAll() {
	  Response response = get("http://localhost:8080/myprisma/webresources/myresource/getActiveUsers");
	  assertEquals("should return status 200", 200, response.getStatusCode());
	  assertNotNull("Should return user list", response.getBody().toString());
	  
	  URL url = LibraryRepository.class.getResource("borrowed.csv");
	  Path path = Paths.get(url.getPath());
	  Assert.assertFalse(Files.exists(path));
	  System.out.println(response.getStatusCode());
	  System.out.println(response.getBody().toString());
	 }
	 
	 @Test
	 public void testFileExist() {
	 URL urlUser = LibraryRepository.class.getResource("user.csv");
	 Path pathUser = Paths.get(urlUser.getPath());
	 Assert.assertFalse(Files.exists(pathUser));
	  
	  URL urlBorrowed = LibraryRepository.class.getResource("borrowed.csv");
	  Path pathBorrowed  = Paths.get(urlBorrowed .getPath());
	  Assert.assertFalse(Files.exists(pathBorrowed ));
	 }
	
	@Test
	void testGetDeactiveUsers(){
        Response response = get("http://localhost:8080/myprisma/webresources/myresource/getDeactiveUsers");
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        Assert.assertEquals(statusCode,HttpStatus.SC_NOT_FOUND);
    }
	
	 @Test
	 public void testFetchDAll() {
	  Response response = get("http://localhost:8080/myprisma/webresources/myresource/getDeactiveUsers");
	  assertEquals("should return status 200", 200, response.getStatusCode());
	  assertNotNull("Should return user list", response.getBody().toString());
	  System.out.println(response.getStatusCode());
	  System.out.println(response.getBody().toString());
	 }
}
