class Solution {
    private final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // Convert all dates to absolute days of the year
        int startA = convertToDayOfYear(arriveAlice);
        int endA = convertToDayOfYear(leaveAlice);
        int startB = convertToDayOfYear(arriveBob);
        int endB = convertToDayOfYear(leaveBob);

        // Find the overlapping boundary
        int overlapStart = Math.max(startA, startB);
        int overlapEnd = Math.min(endA, endB);

        // Calculate overlapping days
        if (overlapStart <= overlapEnd) {
            return overlapEnd - overlapStart + 1;
        }
        
        return 0;
    }

    private int convertToDayOfYear(String date) {
        // Parse month and day from the "MM-DD" format
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int dayCount = 0;
        // Sum up days of all previous months
        for (int i = 0; i < month - 1; i++) {
            dayCount += daysInMonth[i];
        }

        // Add the days of the current month
        return dayCount + day;
    }
}