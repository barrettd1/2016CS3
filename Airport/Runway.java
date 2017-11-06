

public class Runway
{
   private int minutesForLanding; // Minutes for a landing
   private int minutesForTakeOff;
   private int activityTimeLeft;   // minutes until this runway is no longer busy

   
   // This runway has been initialized so that it takes l
   //  minutes to complete a landing, and t minutes to complete a takeoff.
  
   public Runway(int l, int t)
   {
       minutesForLanding = l;
       minutesForTakeOff = t;
       activityTimeLeft =0;
   }


   /**
   * Determine whether this runway is currently busy.
   * @return
   *   <CODE>true</CODE> if this washer is busy (in a runway activity);
   *   otherwise <CODE>false</CODE>
   **/   
   public boolean isBusy( )
   {
      return (activityTimeLeft > 0);
   }
 

   /**
   * Reduce the remaining time on the runway by one second.
   * <b>Postcondition:</b>
   *   If a plane is departing or taking off, then the remaining time in the current
   *   runway activity has been reduced by one second.
   **/
   public void reduceRemainingTime( )
   {
      if (activityTimeLeft > 0)
         activityTimeLeft--;
   } 


   /**
   * Start an activity on this runway.
   * <b>Precondition:</b>
   *   <CODE>isBusy()</CODE> is <CODE>false</CODE>.
   * <b>Postcondition:</b>
   *   This washer has started simulating one wash cycle. Therefore, 
   *   <CODE>isBusy()</CODE> will return <CODE>true</CODE> until the required
   *   number of simulated seonds have passed.
   * @exception IllegalStateException
   *   Indicates that this washer is busy.
   **/
   public void startActivityofTakeOff( )
   {
      if (activityTimeLeft > 0)
         throw new IllegalStateException("Runway is already busy.");
      activityTimeLeft = minutesForTakeOff;
   }
   
   public void startActivityofLanding( )
   {
      if (activityTimeLeft > 0)
         throw new IllegalStateException("Runway is already busy.");
      activityTimeLeft = minutesForLanding;
   }
   
}