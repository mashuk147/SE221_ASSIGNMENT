import java.util.Scanner;

class BinarySearchTree {
    int passnNo;
    String name;
    BinarySearchTree left, right;

    public BinarySearchTree(int passnNo) {
        this.passnNo = passnNo;
        this.left = this.right = null;
    }
}

public class BusReservationSystem {
    private static BinarySearchTree root = null;
    private static int[][] busSeat = new int[32][9];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int randomNum = (int) (Math.random() * 1000);

        login();

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    busLists();
                    break;
                case 2:
                    bookTickets(randomNum);
                    break;
                case 3:
                    cancelBooking(randomNum);
                    break;
                case 4:
                    displayBusSeats(choice);
                    break;
                case 5:
                    displayReservationInfo(randomNum);
                    break;
                case 6:
                    System.out.println("\nThank you for using the Bus Reservation System.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice. Please choose a valid option.");
            }
        }
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        String userName = "user";
        String password = "pass";
        String matchUser, matchPass;

        do {
            System.out.print("\nUserName: ");
            matchUser = scanner.nextLine();

            System.out.print("PassWord: ");
            matchPass = scanner.nextLine();

            if (!userName.equals(matchUser) || !password.equals(matchPass)) {
                System.out.println("\nInvalid details. Please try again.");
            }
        } while (!userName.equals(matchUser) || !password.equals(matchPass));

        System.out.println("\nLogged in successfully.");
    }

    private static void displayMainMenu() {
        System.out.println("\n====================================================================");
        System.out.println("\t\t\tBUS RESERVATION");
        System.out.println("=====================================================================");
        System.out.println("\n==================== MAIN MENU =====================");
        System.out.println("   [1] VIEW BUS LIST");
        System.out.println("   [2] BOOK TICKETS");
        System.out.println("   [3] CANCEL BOOKING");
        System.out.println("   [4] BUSES SEATS INFO");
        System.out.println("   [5] RESERVATION INFO");
        System.out.println("   [6] EXIT");
        System.out.print("\n   ENTER YOUR CHOICE: ");
    }

    private static void busLists() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Bus.No\tName\t\t\tDestinations\t\tCharges\t\tTime");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("1\tসুর্যমুখি-১\t ধানমন্ডি হইতে ক্যাম্পাস \t৳.২৫\t\t07:00 AM");
        System.out.println("2\tডলফিন-২৬\tক্যাম্পাস হইতে ধানমন্ডি\t৳.২৫\t\t01:30 PM");
        System.out.println("3\tসুর্যমুখি-২৬\tমিরপুর হইতে ক্যাম্পাস\t৳.২০\t\t03:50 PM");
        System.out.println("4\tডলফিন-১\tক্যাম্পাস হইতে মিরপুর\t৳.২০\t\t01:00 AM");
        System.out.println("5\tসুর্যমুখী-৬\tউত্তরা হইতে ক্যাম্পাস\t৳.৩০\t\t12:05 AM");
        System.out.println("6\tডলফিন-১০\tক্যাম্পাস হইতে উত্তরা\t৳.৩০\t\t09:30 AM");
        System.out.println("7\tসুরযমুখি-১৮\tটঙ্গী হইতে ক্যাম্পাস\t৳.৪০\t\t11:00 PM");
        System.out.println("8\tডলফিন-১১\t্ক্যাম্পাস হইতে টঙ্গী\t৳.৪০\t\t08:15 AM");
        System.out.println("9\tরজনিগন্ধা-১০\tস্পেসাল বাস সার্ভিস\t.৳১০০\t\t04:00 PM");
        System.out.println("\nPRESS 'ENTER' KEY TO CONTINUE ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Wait for Enter key
    }

    private static void bookTickets(int randomNum) {
        Scanner scanner = new Scanner(System.in);
        int choice, seats, seatNumber;

        System.out.print("\nCHOOSE YOUR BUS: ");
        choice = scanner.nextInt();

        if (choice <= 0 || choice > 9) {
            System.out.println("\nENTER VALID BUS NUMBER!!");
            return;
        }

        displayBusSeats(choice);

        System.out.print("\nNO. OF SEATS YOU NEED TO BOOK: ");
        seats = scanner.nextInt();

        if (seats <= 0 || seats > 32) {
            System.out.println("\nENTER VALID SEAT NUMBER (1-32)!!");
            return;
        }

        for (int i = 0; i < seats; i++) {
            System.out.print("\nENTER THE SEAT NUMBER: ");
            seatNumber = scanner.nextInt();

            if (seatNumber <= 0 || seatNumber > 32) {
                System.out.println("\nENTER VALID SEAT NUMBER (1-32)!!");
                i--;
            } else {
                int custId = choice * 1000 + seatNumber;
                busSeat[choice][seatNumber] = 1;
                root = insert(root, custId);
                System.out.println("\nYOUR CUSTOMER ID IS: " + custId);
            }
        }

        System.out.println("\nYOUR RESERVATION NUMBER IS: " + randomNum);
        System.out.println("PLEASE NOTE DOWN YOUR RESERVATION NUMBER FOR CANCEL BOOKING TICKETS!!");
        System.out.println("PRESS 'ENTER' KEY TO CONTINUE ");
        scanner.nextLine(); // Consume the newline character
        scanner.nextLine(); // Wait for Enter key
    }

    private static BinarySearchTree insert(BinarySearchTree r, int custId) {
        if (r == null) {
            r = new BinarySearchTree(custId);
            Scanner scanner = new Scanner(System.in);
            System.out.print("\n   ENTER THE PERSON NAME: ");
            r.name = scanner.nextLine();
        } else {
            if (r.passnNo > custId) {
                r.left = insert(r.left, custId);
            } else if (r.passnNo < custId) {
                r.right = insert(r.right, custId);
            }
        }
        return r;
    }

    private static void displayBusSeats(int choice) {
        for (int i = 1; i <= 32; i++) {
            if (i < 10 && i > 0) {
                System.out.print("0" + i + ".");
            } else {
                System.out.print(i + ".");
            }

            if (busSeat[choice][i] == 0) {
                System.out.print("EMPTY ");
            } else {
                System.out.print("BOOKED");
            }

            System.out.print("         ");

            if (i % 4 == 0) {
                System.out.println();
            }
        }
    }
    private static void displayReservationInfo(BinarySearchTree r, int reservationNo) {
        if (r == null) {
            System.out.println("\nReservation not found!");
            return;
        }

        if (r.passnNo == reservationNo) {
            redColor();
            System.out.println("\n-----------------------------------------------------------------");
            System.out.printf("||              NAME: %-10s                               ||%n", r.name);
            System.out.printf("||              CUSTOMER ID: %-10d                              ||%n", r.passnNo);
            System.out.printf("||              BUS NUMBER: %-10d                                ||%n", r.passnNo / 1000);
            System.out.printf("||              SEAT NUMBER: %-10d                               ||%n", r.passnNo % 100);
            System.out.printf("||              TICKET COST: Rs.%-10d                            ||%n", cost(r));
            System.out.println("-----------------------------------------------------------------");
            resetColor();
            return;
        }

        if (r.passnNo > reservationNo) {
            displayReservationInfo(r.left, reservationNo);
        } else {
            displayReservationInfo(r.right, reservationNo);
        }
    }

    private static Object cost(BinarySearchTree r) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void resetColor() {
		// TODO Auto-generated method stub
		
	}

	private static void redColor() {
		// TODO Auto-generated method stub
		
	}

	private static void displayReservationInfo(int randomNum) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nENTER YOUR RESERVATION NUMBER: ");
        int reservationNo = scanner.nextInt();

        redColor();
        System.out.println("\nReservation Information:");
        resetColor();

        displayReservationInfo(root, reservationNo);
    }
    private static void cancelBooking(BinarySearchTree r, int reservationNo) {
        if (r == null) {
            System.out.println("\nReservation not found!");
            return;
        }

        if (r.passnNo == reservationNo) {
            redColor();
            System.out.println("\n-----------------------------------------------------------------");
            System.out.printf("||              NAME: %-10s                               ||%n", r.name);
            System.out.printf("||              CUSTOMER ID: %-10d                              ||%n", r.passnNo);
            System.out.printf("||              BUS NUMBER: %-10d                                ||%n", r.passnNo / 1000);
            System.out.printf("||              SEAT NUMBER: %-10d                               ||%n", r.passnNo % 100);
            System.out.printf("||              TICKET COST: Rs.%-10d                            ||%n", cost(r));
            System.out.println("-----------------------------------------------------------------");
            resetColor();

            // Confirm cancellation
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nDo you want to cancel this reservation? (Y/N): ");
            char choice = scanner.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                cancelSeats(r.passnNo);
                System.out.println("\nReservation canceled successfully!");
            } else {
                System.out.println("\nReservation cancellation canceled!");
            }

            return;
        }

        if (r.passnNo > reservationNo) {
            cancelBooking(r.left, reservationNo);
        } else {
            cancelBooking(r.right, reservationNo);
        }
    }

    private static void cancelSeats(int custID) {
        int choice, seatCancel;

        System.out.println("\nEnter the bus number for cancellation: ");
        Scanner scanner = null;
		choice = scanner.nextInt();

        System.out.println("\nHow many seats do you want to cancel?");
        seatCancel = scanner.nextInt();

        for (int i = 0; i < seatCancel; i++) {
            System.out.println("\nEnter the seat number to cancel: ");
            int seatNumber = scanner.nextInt();
            busSeat[choice][seatNumber] = 0;
        }

        // Display updated seat status
        System.out.println("\nUpdated seat status:");
        displayBusSeats(choice);
    }

    private static void cancelBooking(int randomNum) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter your reservation number: ");
        int reservationNo = scanner.nextInt();

        redColor();
        System.out.println("\nReservation Cancellation Information:");
        resetColor();

        cancelBooking(root, reservationNo);
    }}
