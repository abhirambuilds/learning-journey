class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        int turns = 0;

        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            turns++;
        }

        // Alice plays on odd-numbered turns (1st, 3rd, etc.)
        return turns % 2 == 1;
    }
}
