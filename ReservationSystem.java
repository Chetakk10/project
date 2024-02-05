import java.util.*;

import myRailway.*;

 
class ReservationSystem {

    
    private final List<Train> trains;
    private final List<Ticket> tickets;
    private final Map<Integer, List<Integer>> reservedSeats;

    public ReservationSystem() {
        trains = new ArrayList<>();
        tickets = new ArrayList<>();
        reservedSeats = new HashMap<>();
    }

    public void displayTrainInformation() {
        // System.out.println("Available Trains:");
        System.out.println("Train\tTrain Name\tSource\tDestination\tDeparture Time\tSeats");

        for (Train train : trains) {
            System.out.println(
                    train.getTrainNumber() + "\t" +
                            train.getTrainName() + "\t" +
                            train.getSource() + "\t" +
                            train.getDestination() + "    \t" +
                            train.getDepartureTime() + "        \t" +
                            train.getAvailableSeats() + "         \t");
        }
    }

    public void bookTicket(String passengerName, int seatNumber, int trainNumber) {
        try {
            Train selectedTrain = null;
            for (Train train : trains) {
                if (train.getTrainNumber() == trainNumber) {
                    selectedTrain = train;
                    break;
                }
            }

            if (selectedTrain == null) {
                throw new IllegalArgumentException("Invalid train number");
            }

            if (seatNumber > 50) {
                throw new IllegalArgumentException("Invalid seat number");
            }

            if (reservedSeats.containsKey(trainNumber) && reservedSeats.get(trainNumber).contains(seatNumber)) {
                throw new RuntimeException("Seat " + seatNumber + " is already booked for train " + trainNumber);
            }

            if (selectedTrain.bookSeat()) {
                Ticket ticket = new Ticket(passengerName, seatNumber, trainNumber);
                tickets.add(ticket);
                reservedSeats.computeIfAbsent(trainNumber, k -> new ArrayList<>()).add(seatNumber);
                System.out.println();
                System.out.println("Hello, " + passengerName + " Your ticket is secured. " +
        "Take a seat at " + seatNumber + " aboard Train " + trainNumber + ". Have a fantastic journey!");

            } else {
                throw new RuntimeException("No available seats for the selected train.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println(" Error: " + e.getMessage());
        }
    }

    public void cancelTicket(String passengerName, int seatNumber, int trainNumber) {
        for (Ticket ticket : tickets) {
            if (ticket.getPassengerName().equalsIgnoreCase(passengerName)
                    && ticket.getSeatNumber() == seatNumber
                    && ticket.getTrainNumber() == trainNumber) {
                Train associatedTrain = getTrainByNumber(trainNumber);
                if (associatedTrain != null) {
                    associatedTrain.cancelSeat();
                }
                tickets.remove(ticket);
                System.out.println();
                System.out.println("Cancellation complete, " + passengerName + " Seat " + seatNumber +
                " on Train " + trainNumber + " is now open. Wishing you smooth journeys ahead!");
        
                return;
            }
        }
        System.err.println("Error: Ticket not found for cancellation.");
    }

    private Train getTrainByNumber(int trainNumber) {
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                return train;
            }
        }
        return null;
    }

    

    public static void main(String[] args) {

        System.out.println(
                "*************************************************************************************************");
        System.out.println(
                "*                             WELCOME TO THE RAILWAY RESERVATION SYSTEM                           *");
        System.out.println(
                "*************************************************************************************************");
        System.out.println();
        System.out.println();
        ReservationSystem reservationSystem = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);

        Train train1 = new Train(1010, "Mumbai EXP", "Mumbai", "Delhi", "13:05", 50);

        Train train2 = new Train(2013, "Delhi EXP", "Delhi", "Jaipur", "7:00", 50);

        Train train3 = new Train(3045, "Kolkata EXP", "Kolkata", "Delhi", "10:00", 50);


        // Adding the predefined trains to the reservation system
        reservationSystem.trains.add(train1);
        reservationSystem.trains.add(train2);
        reservationSystem.trains.add(train3);

        // Implement user interface and application logic here
        while (true) {
            System.out.println();
            System.out.println(

                    "Choose an option:\n1. Display Train Information\n2. Book a Ticket\n3. Cancel a Ticket\n4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                System.out.println("\nAVAILABLE TRAINS:");
                    reservationSystem.displayTrainInformation();
                    break;
                case 2:
                System.out.println("\nBOOK A TICKET:");
                    System.out.print("Enter passenger name: ");
                    String passengerName = scanner.nextLine();
                    System.out.print("Enter seat number: ");
                    int seatNumber = scanner.nextInt();
                    System.out.print("Enter train number: ");
                    int trainNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    reservationSystem.bookTicket(passengerName, seatNumber, trainNumber);
                    break;
                case 3:
                System.out.println("\nCANCEL A TICKET:");
                    System.out.print("Enter passenger name: ");
                    String cancelPassengerName = scanner.nextLine();
                    System.out.print("Enter seat number: ");
                    int cancelSeatNumber = scanner.nextInt();
                    System.out.print("Enter train number: ");
                    int cancelTrainNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    reservationSystem.cancelTicket(cancelPassengerName, cancelSeatNumber, cancelTrainNumber);
                    break;

                case 4:
                System.out.println("\nExiting the program. Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

