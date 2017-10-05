//Danyelle Barrett
//Programming Assignment 3: Airport Simulation
//Due: 10/5/16

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Airport
{
    public static void main(String[] args)
    {
        final int LANDTIME = 4;
        final int TAKEOFFTIME = 2;
        final double ARRIVALPROBABILITY = 0.05;
        final double AVGTIMEBTWNPLANESTOLAND = 20.0;
        final double DEPARTUREPROBABILITY = 0.05;
        final double AVGTIMEBTWNPLANESTOTAKEOFF = 20.0;
        final int MAXTIMEINAIR = 2;
        final int TOTALTIME = 6000;
        
        airportSimulate(LANDTIME, TAKEOFFTIME, ARRIVALPROBABILITY, AVGTIMEBTWNPLANESTOLAND, DEPARTUREPROBABILITY, AVGTIMEBTWNPLANESTOTAKEOFF,
        MAXTIMEINAIR, TOTALTIME);
    }
    
    public static void airportSimulate (int landTime, int takeOffTime, double arrivalProb, double avgTimeBtwnPlanesToLand,
    double departureProb, double avgTimeBtwnPlanesToTakeOff, int maxTimeInAir, int totalTime)
    {
        Queue<Integer> arrivalTimes = new LinkedList<Integer>();  //landing
        BooleanSource arrival = new BooleanSource(arrivalProb);
        Averager waitTimesForLanding = new Averager();
        int crashedPlanes = 0;
        
        Queue<Integer> departureTimes = new LinkedList<Integer>();  //takeoff
        BooleanSource departure =  new BooleanSource(departureProb);
        Averager waitTimesForDeparture = new Averager();
        int currentMinute;
        int next;
        int wait;
       
        Runway airport = new Runway(landTime, takeOffTime);
        
        // Check the precondition:
        if (landTime <= 0 || takeOffTime <= 0 || arrivalProb < 0 || arrivalProb > 1 || totalTime < 0)
             throw new IllegalArgumentException("Values out of range"); 
             
        for (currentMinute = 0; currentMinute < totalTime; currentMinute++)
        {  // Simulate the passage of one minute of time.

            // Check if a new plane has arrived for landing.
            if (arrival.query( ))
                arrivalTimes.add(currentMinute);
                
            //Check if a new plane is ready to take off.
            if(departure.query())
            {
                departureTimes.add(currentMinute);
            }
            
            // Check whether runway is occupied to see if a plane can land (priority first)
            //is a loop so that the next plane will be processed if the current one ran out of fuel and crashed
            while((!airport.isBusy())  &&  (!arrivalTimes.isEmpty())) 
            {
                next = arrivalTimes.remove();
                wait = currentMinute-next;
                if(wait <= maxTimeInAir)  //if the plane has fuel, begin landing and break out of loop
                {
                    waitTimesForLanding.addNumber(wait);
                    airport.startActivityofLanding( );
                    break;
                }
                else //plane ran out of fuel
                {
                     crashedPlanes++;
                     if((!arrivalTimes.isEmpty()))
                        next = arrivalTimes.remove(); //discard the plane with no fuel and process the next one
                     else
                        break;
                }
            }   
            
            //check that runway is not occupied and that there are no planes waiting to land, so a plane can depart.
            if((!airport.isBusy( ))  &&  (arrivalTimes.isEmpty( )) && (!departureTimes.isEmpty()))
            {
                next = departureTimes.remove();
                waitTimesForDeparture.addNumber(currentMinute - next);
                airport.startActivityofTakeOff();
            }
            // Subtract one second from the remaining time in the current runway activity.
            airport.reduceRemainingTime( );
        }
        
        
        DecimalFormat df = new DecimalFormat("0.00");
        //Display of information about the simulation.
         
         System.out.println("\nNumber of planes taken off: " + waitTimesForDeparture.howManyNumbers());
         if (waitTimesForDeparture.howManyNumbers () > 0)
            System.out.println("Average wait time for taking off: " + df.format(waitTimesForDeparture.average()) + " minutes");
            
        System.out.println("Number of planes landed: " + waitTimesForLanding.howManyNumbers());
         if (waitTimesForLanding.howManyNumbers () > 0)
            System.out.println("Average wait time for landing: " + df.format(waitTimesForLanding.average()) + " minutes");
            
        System.out.println("Number of planes crashed: " + crashedPlanes);
    }
    
}
