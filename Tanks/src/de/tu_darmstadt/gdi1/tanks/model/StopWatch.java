package de.tu_darmstadt.gdi1.tanks.model;

/**
  http://www.google.de/url?sa=t&rct=j&q=stopwatch%20java&source=web&cd=11&ved=0CE4QFjAAOAo&url=http%3A%2F%2Fcs.anu.edu.au%2Fstudent%2Fcomp6700%2Flectures%2Fexamples%2Falgorithms%2FStopWatch.java&ei=S27YT8DFLYPftAb-4fCqDw&usg=AFQjCNHAMa9ZabPKQoS-tL9-fylInyBF5Q
   A stopwatch accumulates time when it is running. You can 
   repeatedly start and stop the stopwatch. You can use a
   stopwatch to measure the running time of a program.
*/

public class StopWatch {  
	/**
      Constructs a stopwatch that is in the stopped state
      and has no time accumulated.
   */
   public StopWatch() {  
   	reset();
   }

   /**
      Starts the stopwatch. Time starts accumulating now.
   */
   public void start() {  
   	if (isRunning) return;
      isRunning = true;
      startTime = System.currentTimeMillis();
   }
   
   /**
      Stops the stopwatch. Time stops accumulating and is
      is added to the elapsed time.
   */
   public void stop() {  
   	if (!isRunning) return;
   	isRunning = false;
   	long endTime = System.currentTimeMillis();
   	elapsedTime = elapsedTime + endTime - startTime;
   }
   
   /**
      Returns the total elapsed time.
      @return the total elapsed time
   */
   public long getElapsedTime()
   {  if (isRunning) 
      {  long endTime = System.currentTimeMillis();
         elapsedTime = elapsedTime + endTime - startTime;
         startTime = endTime;
      }
      return elapsedTime;
   }
   
   public void setElapsedTime(long elapsedTime){
	   this.elapsedTime = elapsedTime;
   }
   
   /**
      Stops the watch and resets the elapsed time to 0.
   */
   public void reset() {
   	elapsedTime = 0;
    isRunning = false;
   }
   
   private long elapsedTime;
   private long startTime;
   private boolean isRunning;
}

