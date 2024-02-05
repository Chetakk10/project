package myRailway;

   public class Train {
       private int trainNumber;
       private String trainName;
       private String source;
       private String destination;
       private String departureTime;
       private int maxSeats = 50;
       private int availableSeats;

       public Train(int trainNumber, String trainName, String source, String destination, String departureTime, int maxSeats) {
           this.trainNumber = trainNumber;
           this.trainName = trainName;
           this.source = source;
           this.destination = destination;
           this.departureTime = departureTime;
           this.maxSeats = maxSeats;
           this.availableSeats = maxSeats; // Initially, all seats are available
       }

       public int getTrainNumber() {
           return trainNumber;
       }

       public String getSource() {
           return source;
       }

       public String getDestination() {
           return destination;
       }

       public String getDepartureTime() {
           return departureTime;
       }

       public int getAvailableSeats() {
           return availableSeats;
       }

       public String getTrainName(){
           return trainName;
       }

       public boolean bookSeat() {
           if (availableSeats > 0) {
               availableSeats--;
               return true; // Seat booked successfully
           }
           return false; // No available seats
       }

       public void cancelSeat() {
           if (availableSeats < maxSeats) {
               availableSeats++;
           }
       }
   }

