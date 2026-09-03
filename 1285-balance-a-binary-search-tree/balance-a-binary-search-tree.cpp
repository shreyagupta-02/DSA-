class Solution {
public:
    void inorder(TreeNode* root, vector<int>& vals) {
        if (root == nullptr) return;
        inorder(root->left, vals);
        vals.push_back(root->val);
        inorder(root->right, vals);
    }

    TreeNode* buildBalancedBST(vector<int>& vals, int left, int right) {
        if (left > right) return nullptr;

        int mid = left + (right - left) / 2;
        TreeNode* node = new TreeNode(vals[mid]);

        node->left = buildBalancedBST(vals, left, mid - 1);
        node->right = buildBalancedBST(vals, mid + 1, right);

        return node;
    }

    TreeNode* balanceBST(TreeNode* root) {
        vector<int> vals;
        inorder(root, vals);
        return buildBalancedBST(vals, 0, (int)vals.size() - 1);
    }
};
