BST stands for binary search tree. The mountain is a binary search tree of nodes, each with two children. Each node is a "rest-stop" that the hiker uses to climb from theroot, all the way down to the leaf at the bottom of the "mountain". 

Each rest-stop can hold a variety of resources and obstacles. The three resources are food, a raft, or an axe. There are also two obstacles. A fallen tree, or a river. A single food is consumed each time we descend from one rest stop to another. An axe is required to chop a fallen tree and clear the path, and is consumed upon use. A raft is required to ford a river and is also consumed upon use. 

This program builds the mountain based on a text file like test, with the names of the rest stops and their resources/obstacles following the name. The goal is to print out all the valid paths based on the mountain generated. The output prints out the names of the rest stops used to traverse down the mountain from the root on the left, to the final leaf on the right. Each new row is a distinct path down the mountain. 

I've described in the BSTMountain class how the mountain is generated, but I've also included a picture in a "test-mountain" file that corresponds to the mountain created by test, in order to make mountain generation as well as the formatting of the input with an example. 
