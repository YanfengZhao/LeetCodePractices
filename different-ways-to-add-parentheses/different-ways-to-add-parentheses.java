/*
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]
*/

// Recursion. 
// Traverse every character. When see an operator, split the input string in two, and call the recursive function.
// Then depends on the operator, + - or * the 2 results and add to the return list.
// If there is no operator, that means a integer string is passed in, simply return the int value of the input.

public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ret = new ArrayList<Integer>();
        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            // if the character is a operator split input into two parts and process
            if(c == '+' || c == '-' || c =='*'){
                String p1 = input.substring(0, i);
                String p2 = input.substring(i+1);
                // recursively compute the each part's value and add to p1ret and p2ret
                
                List<Integer> p1ret = diffWaysToCompute(p1);
                List<Integer> p2ret = diffWaysToCompute(p2);
                
                // calculate different results can be generated by using a p1ret and a p2ret
                
                for(int j : p1ret){
                    for(int k : p2ret){
                        if(c == '+'){
                            ret.add(j+k);
                        }
                        else if(c == '-'){
                            ret.add(j-k);
                        }
                        else if(c == '*'){
                            ret.add(j*k);
                        }
                    }
                }
            }
        }
        // if ret contains no element, means the input is a single integer, so just return the integer.
        if(ret.size()==0){
            ret.add(Integer.valueOf(input));
            return ret;
        }
        return ret;
    }
}
