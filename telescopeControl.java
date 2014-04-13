/*
 * STATE: 
 * This class represents the basic interface
 * of a state in the FSM programming model of a telescope.
 * It includes four functions which implement parameters
 * to switch between different states and cope with connections
 * via a network.
 */

abstract class State
{
	public void day(boolean yes){};
	public abstract boolean call();
	public void sendTask(){};
	public void finish(){};

}

/*
 * WHEN TELESCOPE IS IN SLEEP MODE
 */

class sleepState extends State{
	telescopeControl TelescopeControl;
	
	
	public sleepState(telescopeControl telescopeControl) {
		super();
		TelescopeControl = telescopeControl;
	}


	public void day(boolean yes){
		if(!yes){
			TelescopeControl.setState(TelescopeControl.getIdle());
		}
		else
			System.out.println("It's daytime. Continue sleeping.");
	}
	public boolean call(){
		System.out.println("Telescope is sleeping.");
		return false;
	}
}

/*
 * WHEN TELESCOPE IS IDLE
 */

class idleState extends State{
	telescopeControl TelescopeControl;
	
	
	public idleState(telescopeControl telescopeControl) {
		super();
		TelescopeControl = telescopeControl;
	}


	public void day(boolean yes){
		if(yes){
			TelescopeControl.setState(TelescopeControl.getSleep());
		}
		else
			System.out.println("It's night time. Stay idle.");
	}
	public boolean call(){

		TelescopeControl.setState(TelescopeControl.getWait());
		return true;
	}
}

/*
 * WHEN TELESCOPE IS WAITING
 */

class waitState extends State{
telescopeControl TelescopeControl;
	
	
	public waitState(telescopeControl telescopeControl) {
		super();
		TelescopeControl = telescopeControl;
	}


	public void day(boolean yes){
		if(yes){
			TelescopeControl.setState(TelescopeControl.getSleep());
		}
		else
			System.out.println("It's night time. Keep waiting.");
	}
	public boolean call(){
		System.out.println("I'm waiting.");
		return false;
	}
	public void timePassed(){
		System.out.println("Time for task passed. Return to idle.");
		TelescopeControl.setState(TelescopeControl.getIdle());
	}
	public void sendTask(){
		TelescopeControl.setState(TelescopeControl.getBusy());
		//to add arguments
	}
	
	
}

/*
 * WHEN TELESCOPE IS BUSY
 */

class busyState extends State{
telescopeControl TelescopeControl;
	
	
	public busyState(telescopeControl telescopeControl) {
		super();
		TelescopeControl = telescopeControl;
	}


	public void day(boolean yes){
		if(yes){
			TelescopeControl.setState(TelescopeControl.getSleep());
		}
		else
			System.out.println("It's night time. Keep working.");
	}
	public boolean call(){
		
		System.out.println("I'm busy.");
		return false;
	}
	
	public void finish(){
		System.out.println("Job done.");
		TelescopeControl.setState(TelescopeControl.getIdle());
	}
	
}





public class telescopeControl {
	
	State sleep;
	State idle;
	State wait;
	State busy;
	
	State currentState = sleep;
	
	
	
	public telescopeControl() {
		super();
		this.sleep = new sleepState(this);
		this.idle = new idleState(this);
		this.wait = new waitState(this);
		this.busy = new busyState(this);;
	}
	public void day(boolean yes){
		currentState.day(yes);
	}
	public boolean call(){
		return currentState.call();	
	}
	public void sendTask(){
		currentState.sendTask();
	}
	public void finish(){
		currentState.finish();
	}
	public void setState(State newState){
		currentState = newState;
	}
	
	

	public State getSleep() {
		return sleep;
	}
	public State getIdle() {
		return idle;
	}
	public State getWait() {
		return wait;
	}
	public State getBusy() {
		return busy;
	}
	public State getCurrentState() {
		return currentState;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
