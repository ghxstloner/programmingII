package unit3;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clock class represents a simple digital clock that continuously updates and displays the current time and date.
 * It provides methods to update the current time and display it concurrently using Java threads.
 */
class Clock {

    // Volatile variable to ensure the current date and time are updated and visible across multiple threads.
    private volatile String currentDateTime;

    /**
     * Constructor initializes the clock with the current time and date.
     */
    public Clock() {
        updateCurrentTime();
    }

    /**
     * Continuously updates the current time and date every second.
     * This method is designed to run in a background thread.
     */
    public void updateTime() {
        while (true) {
            try {
                // Update the current date and time
                updateCurrentTime();
                // Sleep for 1 second before the next update
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Handle the case where the thread is interrupted during sleep
                System.err.println("Update thread interrupted: " + e.getMessage());
            }
        }
    }

    /**
     * Continuously displays the current time and date every second.
     * This method is designed to run in a separate thread with a higher priority.
     */
    public void displayTime() {
        while (true) {
            // Print the current time and date to the console
            System.out.println("Current Time: " + currentDateTime);
            try {
                // Sleep for 1 second before displaying the next update
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Handle the case where the thread is interrupted during sleep
                System.err.println("Display thread interrupted: " + e.getMessage());
            }
        }
    }

    /**
     * Updates the current time and date by formatting the system's current date.
     * This method is synchronized to ensure consistent updates when accessed by multiple threads.
     */
    private synchronized void updateCurrentTime() {
        // Format the current date and time in the format HH:mm:ss dd-MM-yyyy
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        currentDateTime = formatter.format(new Date());
    }
}
