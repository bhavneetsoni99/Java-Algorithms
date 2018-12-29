//package com.google.challenges;
import java.util.ArrayList;
import java.util.Arrays;

public class Answer {
    public static int answer(int[][] maze) {
        int height = maze.length;
        int width = maze[0].length;
        int[] values = new int[height * width];
        int k = 0;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                values[k] = maze[h][w];
                k++;
                ArrayList<Integer> mIndex = new ArrayList<Integer>();
                try {
                    int random = maze[h + 1][w];
                    mIndex.add((h + 1) * width + w);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    int random = maze[h][w + 1];
                    mIndex.add((h) * width + w + 1);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    int random = maze[h][w - 1];
                    mIndex.add((h) * width + w - 1);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                try {
                    int random = maze[h - 1][w];
                    mIndex.add((h - 1) * width + w);

                } catch (ArrayIndexOutOfBoundsException e) {

                }
                moves.add(mIndex);

            }
        }
        ArrayList<Integer> visited = new ArrayList<Integer>();
        int start = 0;
        visited.add(0);
        getValidPaths(maze, values, visited, start, height * width - 1, 0);
        int minNodes = path.size();

        found = false;
        moves.clear();
        firstFound = false;
        path.clear();
        return minNodes;
    }

    private static ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
    private static ArrayList<Integer> path = new ArrayList<>();

    private static boolean found = false;
    private  static boolean firstFound = false;

    private static void getValidPaths(int[][] maze, int[] values, ArrayList<Integer> visited, int start, int end, int onesCount) {
        if (start == end) {
            path.clear();
            firstFound =true;
            if (visited.size() == maze.length + maze[0].length - 1) {
                found = true;
            }
            path.addAll(visited);
            return;
        } else {
            if (!found) {
                ArrayList<Integer> level = moves.get(start);
                for (int i = 0; i < level.size(); i++) {

                    int node = level.get(i);

                    ArrayList<Integer> temp = new ArrayList<Integer>();

                    if (visited.contains(node)) {
                        continue;
                    }
                    int onesCountNode = onesCount + values[node];
                    if (onesCountNode >1) {
                        continue;
                    }
                    temp.addAll(visited);
                    temp.add(node);
                    if(firstFound) {
                        if (temp.size() < path.size()) {
                            getValidPaths(maze, values, temp, node, end, onesCountNode);
                        }
                    }else {
                        getValidPaths(maze, values, temp, node, end, onesCountNode);
                    }
                }
            }
        }
    }
}