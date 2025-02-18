//The idea here is to solve the problem using BFS. 
//We created an indegrees array to keep track of the number of dependencies each course has, and to start the traversal with the independent courses
//For effiecient lookups for the dependencies of each course, we take a hashmap of adjacency lists to represent the graph
//Time Complexity: O(V+E) where V is the no. of courses and E is the length of the prerequisites array
//Space Complexity: O(V+E)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses == 0){
            return true;
        }
        int indegrees[] = new int[numCourses]; // how many dependencies on each course
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int n = prerequisites.length;
        for(int i=0; i<n; i++){ //O(E)
            int ind = prerequisites[i][1];
            int dep = prerequisites[i][0];
            indegrees[dep]++; // increases the value of dependencies by 1 at index of that course
            if(!map.containsKey(ind)){
                map.put(ind, new ArrayList<>());
            }
            map.get(ind).add(dep);
        }
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i<numCourses; i++){ // iterating through the indegrees array //O(V)
            if(indegrees[i] == 0){
                q.add(i); // adding that course to queue, means the course is taken
                count++;
            }
        }
        if(count == numCourses) return true; //all courses are independent
        if(q.isEmpty()) return false; //all the courses are dependent on each other(cycle), no independent course available
        //Now BFS
        while(!q.isEmpty()){ //O(V+E)
            int curr = q.poll();
            //children
            List<Integer> children = map.get(curr);
            if (children == null) continue; //there could be few independent courses(vertices), no ones depend on them so they have empty list in map
            for(Integer child: children){
                indegrees[child]--;
                if(indegrees[child] == 0){
                    q.add(child);
                    count++;
                    if(count == numCourses){ // we are checking the count the moment a new course is added to queue, if it is added, it means that course is taken
                        return true;
                    }
                }
            }
        }
        return false; //the count is not equal to numcourses and it means the courses cannot be taken

    }
}
