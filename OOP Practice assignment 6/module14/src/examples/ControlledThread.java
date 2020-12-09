package examples;

public class ControlledThread extends Thread {
  private          boolean suspended=false; // for suspend/resume
  private volatile boolean stopped=false;   // can be set only once to true

  public ControlledThread() {
    super();// may be implicit
  }

  public ControlledThread(Runnable runner) {
    super(runner);
  }

  public  synchronized void suspendRequest() {
    myCheckAccess(); // is the caller in the same ThreadGroup?
    suspended = true;
  }
  public  synchronized void stopRequest() {
    myCheckAccess();
    stopped = true;
    if ( suspended ) { // if you sleep be awakened before you die!
      notify();
    }
  }
  public  synchronized void resumeRequest() {
    myCheckAccess();
    if ( suspended ) {
      suspended = false;
      notify();
    }
  }
  public synchronized void myCheckAccess() {
    checkAccess();  // call the real checkAccess()
    if ( this != currentThread() ) {
      throw new SecurityException(
	 "external thread cannot call myCheckAccess()");
    }
    // wait if the thread is suspended, but not stopped
    while ( suspended && !stopped ) {
      try {
	wait();
      } catch (InterruptedException exc) {
	// do nothing
      }
    }
  }
}
