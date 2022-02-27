package com.algorithm.homework.weekeight.p684;

/**
 * @author qiuch
 * Time complexity : O(nlogn)
 * Space complexity : O(n)
 */
class Solution {
    int[] fa;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        fa = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fa[i] = i;
        }

        for (int[] edge : edges) {
            if (!union(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[0];
    }

    boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            fa[x] = y;
            return true;
        }
        return false;
    }

    int find(int x) {
        if (x == fa[x]) {
            return x;
        }
        return fa[x] = find(fa[x]);
    }
}

