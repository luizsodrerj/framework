package timediff;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/service")
public class TimeDiffService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response calcDiff(ParamsCalcDiff params) {
		TimeDiff diff = new TimeDiff();
		String start  = params.getDateStart();
		String stop	  = params.getDateStop();
		
		TimeDiffResult result = diff.calcDiff(start, stop); 
		
		return Response.ok().entity(result).build();
	}

}
