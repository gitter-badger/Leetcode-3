package stevesun.algorithms;

import java.util.*;

/**
 * Created by fishercoder1534 on 9/30/16.
 */
public class SurroundedRegionsBFS {
    /**I won't call this problem hard, it's just confusing, you'll definitely want to clarify what the problem means before coding.
     * This problem eactually means:
     * any grid that is 'O' but on the four edges, will never be marked to 'X';
     * furthermore, any grid that is 'O' and that is connected with the above type of 'O' will never be marked to 'X' as well;
     * only all other nodes that has any one direct neighbor that is an 'X' will be marked to 'X'.*/


    int[] dirs = new int[]{0,1,0,-1,0};

    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) return;
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList();
        //check first row and last row and mark all those '0' on these two rows to be '+' to let them be different from other 'O', at the same time, we put them into the queue to get ready for a BFS to mark all those adjacent 'O' nodes to '+' as well
        for(int j = 0; j < n; j++){
            if(board[0][j] == 'O') {
                board[0][j] = '+';
                queue.offer(new int[]{0,j});

            }
            if(board[m-1][j] == 'O') {
                board[m-1][j] = '+';
                queue.offer(new int[]{m-1,j});
            }
        }

        //check first column and last column too
        for(int i = 0; i < m; i++){
            if(board[i][0] == 'O') {
                board[i][0] = '+';
                queue.offer(new int[]{i, 0});
            }
            if(board[i][n-1] == 'O') {
                board[i][n-1] = '+';
                queue.offer(new int[]{i,n-1});
            }
        }

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            for(int i = 0; i < 4; i++){
                int x = curr[0]+dirs[i];
                int y = curr[1]+dirs[i+1];
                if(x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O'){
                    board[x][y] = '+';
                    queue.offer(new int[]{x,y});
                }
            }
        }

        //now we can safely mark all other 'O' to 'X', also remember to put those '+' back to 'O'
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O') board[i][j] = 'X';
                else if(board[i][j] == '+') board[i][j] = 'O';
            }
        }
    }

}
