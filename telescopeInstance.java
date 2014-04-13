import java.util.*;
import java.io.*;

/*
 * Horizontal coordinates
 */
class earthCoordinates{
	float longitude;
	float lattitude;
	public earthCoordinates(float longitude, float lattitude) {
		super();
		this.longitude = longitude;
		this.lattitude = lattitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public float getLattitude() {
		return lattitude;
	}
	public boolean compareLong(earthCoordinates ec){
		if(ec.longitude<this.longitude)
			return false;
		else
			return true;
	}
	
}

class spaceCoordinates{
	float rightAscension;
	float declination;
	public spaceCoordinates(float rightAscension, float declination) {
		super();
		this.rightAscension = rightAscension;
		this.declination = declination;
	}
	public float getRightAscension() {
		return rightAscension;
	}
	public float getDeclination() {
		return declination;
	}
	
}

/*
 * TELESCOPE INSTANCE:
 * 
 */

public class telescopeInstance {
	telescopeControl control = new telescopeControl();
	String telescopeName;
	String ownerName;
	String IPaddress;
	earthCoordinates geoCoordinates;
	spaceCoordinates astroCoordinates;
	
	public telescopeInstance(String telescopeName, String ownerName,
			earthCoordinates geoCoordinates, String IPaddress) {
		super();
		this.telescopeName = telescopeName;
		this.ownerName = ownerName;
		this.geoCoordinates = geoCoordinates;
		this.IPaddress = IPaddress;
	}
	
	public double getDistance(earthCoordinates geoCoords){
		double R = 6373.0;
		double lat1 = Math.toRadians(geoCoords.getLattitude());
		double lon1 = Math.toRadians(geoCoords.getLongitude());
		double lat2 = Math.toRadians(geoCoordinates.getLattitude());
		double lon2 = Math.toRadians(geoCoordinates.getLongitude());
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.sin(dlat/2)*2 + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlon/2)*2;
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return R*c;
	}
	public boolean call(){
		return control.call();
	}
	public void sendTask(spaceCoordinates sc){
		astroCoordinates = sc;
	}
	public result finish(){
		control.finish();
		result photo = this.takePhoto();
		return photo;
	}
	public result takePhoto(){
		return new result();
	}
	
	}
