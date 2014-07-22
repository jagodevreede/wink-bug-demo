package org.example.rest1;

import org.osgi.service.log.LogService;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@Path("example1")
public class ExampleComponent {
	private volatile LogService m_log;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public String example() {
    	return "example";
    }	
}
