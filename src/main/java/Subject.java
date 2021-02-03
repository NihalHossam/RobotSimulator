// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package main.java.softdesign;

public interface Subject {
	/*
	 * Elements: 1)observers of the type Observer which are subscribed to receive notifications from the
	 * Subject when Subject's status has changed;
	 * 			 2) status specifying the current state of the Subject
	 * Structure: linear;
	 * Domain: Objects of the type Robot implements Observer can subscribe to receive notifications from the Subject.
	 */

	public void detach(Observer observer);
	/* PRE  - 
	   POST - The list of observers does not contain the observer.
	*/

	public void attach(Observer observer);
	/* PRE  - 
	   POST - The list of observers contains the observer.
	*/

	public void notifyAllObservers();
	/* PRE  - status of the Subject has been changed
	   POST - change in the task status has been broadcasted to all attached observers.
	*/
};
