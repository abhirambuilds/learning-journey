/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left=0;
        int right=n;
        while(left<=right){
            int g=left+(right-left)/2;
            int result=guess(g);
            if(result==-1){
                right=g-1;
            }else if(result==1){
                left=g+1;
            }else{
                return g;
            }
        }
        return n;
    }
}