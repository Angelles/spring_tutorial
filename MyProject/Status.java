package MyProject;

public enum Status {
    CANCELLED(), // should be selected if there is an end date
    COMPLETED(),
    IN_PROGRESS(),
    /*
     System.out.println("Enter one of the following numbers to update the project status:\n" +
                "0 : NONE\n1 : BLOCKED\n2 : DONE\n3 : IN PROGRESS\n4 : NOT STARTED\n");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        scanner.close();
        if (i == 0) {
            this.current_status = Status.NONE;
        } else if (i == 1) {
            this.current_status = Status.BLOCKED;
        } else if (i == 2) {
            this.current_status = Status.DONE;
        } else if (i == 3) {
            this.current_status = Status.IN_PROGRESS;
        } else if (i == 4) {
            this.current_status = Status.NOT_STARTED;
        } else {
            this.current_status = Status.NONE;
        }
     */
}
