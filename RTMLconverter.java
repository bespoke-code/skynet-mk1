/*
 * RTML CONVERTER:
 * A basic RTML parser implementation.
 * Serves as a concept for further developers
 * to better visualize the parser nature of the converter.
 * Methods included provide functions to extract information
 * from a RTML file (XML based) and use them as inputs in
 * different operation modes at all locations included in the
 * robotic telescope network.
 */

public class RTMLconverter {
	public static String getMode(String s){
		int start = s.indexOf("mode")+4;
		while(s.charAt(start)!='"'){
			start++;
		}
		start++;
		String p="";
		while(s.charAt(start)!='"'){
			if(s.charAt(start)!=' '){
				p=p+s.charAt(start);
			}
			start++;
		}
		return p;
	}
	public static String getName(String s){
		int start = s.indexOf("Camera")+6;
		while(s.charAt(start)!='"'){
			start++;
		}
		start++;
		String p="";
		while(s.charAt(start)!='"'){
			if(s.charAt(start)!=' '){
				p=p+s.charAt(start);
			}
			start++;
		}
		return p;
		
	}
	public static float getRightAscension(String s){
		int start = s.indexOf("hours") + 6;
		while(s.charAt(start)!='>'){
			start++;
		}
		start++;
		String p="";
		while(s.charAt(start)!='<'){
			if(s.charAt(start)!=' '){
				p=p+s.charAt(start);
			}
			start++;
		}
		return Float.parseFloat(p);
		
	}
	public static float getDeclination(String s){
		int start = s.indexOf("degrees") + 6;
		while(s.charAt(start)!='>'){
			start++;
		}
		start++;
		String p="";
		while(s.charAt(start)!='<'){
			if(s.charAt(start)!=' '){
				p=p+s.charAt(start);
			}
			start++;
		}
		return Float.parseFloat(p);
	}
	public static int getExposureCount(String s){
		int start = s.indexOf("Exposure")+6;
		while(s.charAt(start)!='"'){
			start++;
		}
		start++;
		String p="";
		while(s.charAt(start)!='"'){
			if(s.charAt(start)!=' '){
				p=p+s.charAt(start);
			}
			start++;
		}
		return Integer.parseInt(p);
	}
	public static float getSeconds(String s){
		int start = s.indexOf("seconds") + 6;
		while(s.charAt(start)!='>'){
			start++;
		}
		start++;
		String p="";
		while(s.charAt(start)!='<'){
			if(s.charAt(start)!=' '){
				p=p+s.charAt(start);
			}
			start++;
		}
		return Float.parseFloat(p);
	}
	public static String setRTML(String name, float hours, float degrees, int count, float seconds){
		String rtml ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<RTML mode=\"offer\" uid=\"rtml://uk.ac.ex/users/tnaylor\" version\"3.1a\"\nxmlns=\"http://www.rtml.org/v3.1a\"\nxmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\nxsi:schemaLocation=\"http://www.rtml.org/v3.1a http://monet.uni-sw.gwdg.de/XMLSchema/RTML/schemas/RTML-nightly.xsd\" >\n<Camera name=\"CAMERANAME\"/>\n<Target name=\"Andromeda\" id=\"andromeda\">\n<Coordinates>\n<RightAscension><Value units=\"hours\">HOURS</Value></RightAscension>\n<Declination><Value units=\"degrees\">DEGREES</Value></Declination>\n</Coordinates>\n</Target>\n<Schedule>\n<Exposure count=\"COUNT\">\n<Value units=\"seconds\">SECONDS</Value>\n</Exposure>\n</Schedule>\n</RTML>";
		rtml=rtml.replaceAll("CAMERANAME", name);
		rtml=rtml.replaceAll("HOURS", String.format("%f", hours));
		rtml=rtml.replaceAll("DEGREES", String.format("%f", degrees));
		rtml=rtml.replaceAll("SECONDS", String.format("%f", seconds));
		rtml=rtml.replaceAll("COUNT", String.format("%d", count));
		return rtml;
		
	}
	public static void main(String[] args) {
		
		String s = setRTML("ImeNaKamera", (float)53.0, (float)32.2, 2, (float)250.0);
		System.out.println(s);
		System.out.println(getMode(s));
		System.out.println(getName(s));
		System.out.println(getRightAscension(s));
		System.out.println(getDeclination(s));
		System.out.println(getExposureCount(s));
		System.out.println(getSeconds(s));
		
	}
}
