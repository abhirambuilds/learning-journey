class Solution {
    public void setZeroes(int[][] matrix) {
        int rows=matrix.length;
        int cols=matrix[0].length;
        boolean row[]=new boolean[rows];
        boolean col[]=new boolean[cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(matrix[i][j]==0){
                    row[i]=true;
                    col[j]=true;
                }
            }
        }
        for(int i=0;i<rows;i++){
            if(row[i]){
                for(int j=0;j<cols;j++){
                    matrix[i][j]=0;
                }
            }
        }
        for(int j=0;j<cols;j++){
            if(col[j]){
                for(int i=0;i<rows;i++){
                    matrix[i][j]=0;
                }
            }
        }
    }
}