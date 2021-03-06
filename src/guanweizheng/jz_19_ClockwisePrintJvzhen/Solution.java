package guanweizheng.jz_19_ClockwisePrintJvzhen;

// 题目描述：
//         输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
//         例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
//         则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
import java.util.ArrayList;

public class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int up = 0,down = matrix.length-1,left = 0,right = matrix[0].length-1;
        ArrayList<Integer> list = new ArrayList<>();
        if(matrix == null||matrix.length == 0||matrix[0].length == 0){
            return list;
        }
        while(true){
            for(int i=left;i<=right;i++){
                list.add(matrix[up][i]);
            }
            up++;
            if(up>down) break;
            for(int i=up;i<=down;i++){
                list.add(matrix[i][right]);
            }
            right--;
            if(left>right) break;
            for(int i=right;i >= left;i--){
                list.add(matrix[down][i]);
            }
            down--;
            if(up>down) break;
            for(int i= down;i>= up;i--){
                list.add(matrix[i][left]);
            }
            left++;
            if(left>right) break;
        }
        return list;
    }

}
