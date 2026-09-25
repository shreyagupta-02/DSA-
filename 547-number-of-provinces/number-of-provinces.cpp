class Solution {
public:
    int findCircleNum(vector<vector<int>>& isConnected) {
        int n = isConnected.size();
        vector<bool>visited(n,false);
        int provinces=0;

        for(int i=0;i<n;i++){
            if(visited[i]==false){
                provinces++;
                queue<int>q;
                q.push(i);
                visited[i]=true;
                while(!q.empty()){
                    int city =q.front();
                    q.pop();

                    for(int j=0;j<n;j++){
                        if(isConnected[city][j]==1&&visited[j]==false){
                            visited[j]=true;
                            q.push(j);
                        }
                    }
                }
            }
        }
        return provinces;
    }
};