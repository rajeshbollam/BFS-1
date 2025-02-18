//In this BFS traversal approach, we first take the root node and place it in a queue. 
//Now, while this queue is not empty, we keep adding the values of the nodes to a new arraylist and adding the children of the nodes into the queue.
//We also keep a size variable as we need to have a distinction at each level, to have nodes at each level in a separate list.
//After each level, we add the new arraylist to the result.
//Time complexity: O(n)
//Space Complexity: O(n)
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i<size; i++){
                TreeNode node = queue.poll();
                level.add(node.val); 
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }                               
            }
            result.add(level);            
        }
        return result;
    }
}

//In this approach, we use dfs for level order traversing
//At each node, we keep the track of the level of the node and add the node value to a new list if the depth of the node is equal to the size of the result and add the list to the result
//If at a node there is already a list exists, then we simply add the node value to that list in the result.
//Time Complexity:O(n)
//Space Complexity: O(h) for recursive stack space, where h is the maximum depth of the tree from root to a leaf node.
class Solution1 {
    List<List<Integer>> result;
    public List<List<Integer>> levelOrder(TreeNode root) {
        result = new ArrayList<>();
        dfs(root, 0);
        return result;        
    }

    private void dfs(TreeNode root, int depth){
        //base
        if(root == null){
            return;
        }

        //logic
        if(result.size() == depth){
            result.add(new ArrayList<>());
        }

        result.get(depth).add(root.val);        

        dfs(root.left, depth+1);
        dfs(root.right, depth+1);

    }
}