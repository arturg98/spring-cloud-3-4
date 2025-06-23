package pl.ag.springcloudconfigserver;

import java.util.Random;

public class PolishIdGenerator {

    private static final String ALLOWED_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int[] CHECKSUM_WEIGHTS = {7, 3, 1, 9, 7, 3, 1, 7, 3};
    private static final int ID_LENGTH = 9;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        String idNumber = generateValidIdNumber();
        System.out.println("Generated Polish ID number: " + idNumber);
    }

    public static String generateValidIdNumber() {
        while (true) {
            StringBuilder idBuilder = new StringBuilder(ID_LENGTH);

            // Generate 3-letter series
            for (int i = 0; i < 3; i++) {
                char randomLetter = ALLOWED_LETTERS.charAt(RANDOM.nextInt(ALLOWED_LETTERS.length()));
                idBuilder.append(randomLetter);
            }

            // Generate 6-digit number
            for (int i = 0; i < 6; i++) {
                idBuilder.append(RANDOM.nextInt(10));
            }

            String idCandidate = idBuilder.toString();
            if (isValidIdNumber(idCandidate)) {
                return idCandidate;
            }
        }
    }

    public static boolean isValidIdNumber(String id) {
        if (id == null || !id.matches("[A-Z]{3}\\d{6}")) {
            return false;
        }

        int checksum = 0;
        for (int i = 0; i < ID_LENGTH; i++) {
            int value = (i < 3)
                    ? id.charAt(i) - 'A' + 10 // Convert letter to numeric value
                    : id.charAt(i) - '0';     // Convert digit
            checksum += value * CHECKSUM_WEIGHTS[i];
        }

        return checksum % 10 == 0;
    }
}