import java.util.*;
import java.io.*;

/*
 * TELESCOPE SERVER:
 * This class implements the telescope server program
 * which is to run on a server where different telescopes
 * are logged and remembered to form the robotic telescope network.
 * This class implements methods to add telescopes to the server,
 * send target signals to two optimally positioned telescopes for a single threat
 * which would then track the given object and send back a RESULT as a picture,
 * scientific formulas or what not - depending on their resources,
 * network architecture and other parameters such as telescope load or
 * weather conditions / calculations scheduled on the local system.
 */

public class telescopeServer {
	Map<earthCoordinates,telescopeInstance> telescopes = new HashMap<earthCoordinates,telescopeInstance>();
	
	public void addTelescope(){
		Scanner s = new Scanner(System.in);
		String telescopeName = s.next();
		String ownerName = s.next();
		String IPaddress = s.next();
		
		float longitude = s.nextFloat();
		float lattitude = s.nextFloat();
		
		earthCoordinates geoCoordinates = new earthCoordinates(longitude, lattitude);
		telescopeInstance telescope = new telescopeInstance(telescopeName, ownerName, geoCoordinates, IPaddress);
		telescopes.put(geoCoordinates, telescope);
	}
	
	public void target(){
		Scanner s = new Scanner(System.in);
		
		
		telescopeInstance min = null;
		telescopeInstance max = null;
		for (telescopeInstance ts : telescopes.values()) {
			if(min!=null){
				if(!min.geoCoordinates.compareLong(ts.geoCoordinates))
					min=ts;
				
			}
			if(max!=null){
				if(max.geoCoordinates.compareLong(ts.geoCoordinates))
					max=ts;
			}
			if(min==null)
				min=ts;
			if(max==null)
				max=ts;
		}
		
		int count = 0;
		result result1 = min.finish();
		result result2 = max.finish();
		
		
	}
	
	public static void main(String[] args) {
		telescopeServer server = new telescopeServer();
		Scanner scanner = new Scanner(System.in);
		while(true){
			String temp = scanner.nextLine();
			if(temp.equals("Add telescope"))
				server.addTelescope();
			if(temp.equals("Target threat"))
				server.target();
			
		}
		
	}
	
}
