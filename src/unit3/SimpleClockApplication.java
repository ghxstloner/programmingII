package unit3;

/**
 * The main class that drives the Simple Clock Application.
 * It creates two threads, one for updating the clock's time and another for displaying it.
 * The display thread is assigned a higher priority than the update thread to ensure the clock is shown promptly.
 */
public class SimpleClockApplication {

    /**
     * Main method that starts the application and manages the threads for updating and displaying the time.
     */
    public static void main(String[] args) {
        // Create a new instance of the Clock class
        Clock clock = new Clock();

        // Create the thread responsible for updating the clock's time (lower priority)
        Thread updateThread = new Thread(clock::updateTime);
        updateThread.setPriority(Thread.MIN_PRIORITY); // Set lower priority to the update thread

        // Create the thread responsible for displaying the current time (higher priority)
        Thread displayThread = new Thread(clock::displayTime);
        displayThread.setPriority(Thread.MAX_PRIORITY); // Set higher priority to the display thread

        // Start both threads
        updateThread.start();
        displayThread.start();
    }
}
