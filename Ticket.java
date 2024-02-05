package myRailway;

    public class Ticket {
        private static int ticketCounter = 1;
        private int ticketNumber;
        private String passengerName;
        private int seatNumber;
        private int trainNumber;

        public Ticket(String passengerName, int seatNumber, int trainNumber) {
            this.ticketNumber = ticketCounter++;
            this.passengerName = passengerName;
            this.seatNumber = seatNumber;
            this.trainNumber = trainNumber;
        }

        public int getTicketNumber() {
            return ticketNumber;
        }

        public String getPassengerName() {
            return passengerName;
        }
        public int getSeatNumber() {
            return seatNumber;
        }

        public int getTrainNumber() {
            return trainNumber;
        }
    }
