package com.algorithm.homework.weekeight.p200;

/**
 * @author qiuch
 * Time complexity : O(m*n)
 * Space complexity : O(m*n)
 */
class Solution {
    int ans, rowNum, columnNum;
    int[] parent, rank;// rank往数量多的集合里合并

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        rowNum = grid.length;
        columnNum = grid[0].length;
        init(grid);
        for (int rowIndex = 0; rowIndex < rowNum; ++rowIndex) {
            for (int columnIndex = 0; columnIndex < columnNum; ++columnIndex) {
                if (grid[rowIndex][columnIndex] == '1') {
                    grid[rowIndex][columnIndex] = '0';
                    if (rowIndex - 1 >= 0 && grid[rowIndex - 1][columnIndex] == '1') {
                        union(rowIndex * columnNum + columnIndex, (rowIndex - 1) * columnNum + columnIndex);
                    }
                    if (rowIndex + 1 < rowNum && grid[rowIndex + 1][columnIndex] == '1') {
                        union(rowIndex * columnNum + columnIndex, (rowIndex + 1) * columnNum + columnIndex);
                    }
                    if (columnIndex - 1 >= 0 && grid[rowIndex][columnIndex - 1] == '1') {
                        union(rowIndex * columnNum + columnIndex, rowIndex * columnNum + columnIndex - 1);
                    }
                    if (columnIndex + 1 < columnNum && grid[rowIndex][columnIndex + 1] == '1') {
                        union(rowIndex * columnNum + columnIndex, rowIndex * columnNum + columnIndex + 1);
                    }
                }
            }
        }

        return ans;
    }


    public void init(char[][] grid) {
        ans = 0;
        parent = new int[rowNum * columnNum];
        rank = new int[rowNum * columnNum];
        for (int i = 0; i < rowNum; ++i) {
            for (int j = 0; j < columnNum; ++j) {
                if (grid[i][j] == '1') {
                    parent[i * columnNum + j] = i * columnNum + j;
                    ++ans;
                }
                rank[i * columnNum + j] = 0;
            }
        }
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            if (rank[rootx] > rank[rooty]) {
                parent[rooty] = rootx;
            } else if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty;
            } else {
                parent[rooty] = rootx;
                rank[rootx] += 1;
            }
            --ans;

        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution().numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        }));
    }
}

