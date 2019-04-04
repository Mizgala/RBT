package rtree

class RecursiveBinaryTree <T>(gelem: T, feval: (T,T) -> Boolean) {
    val elem : T = gelem
    // eval is assumed to act as a less-than function
    val eval = feval
    private var leaves : Pair<RecursiveBinaryTree<T>?, RecursiveBinaryTree<T>?> = pairOf(null)

    private fun pairOf (elem : RecursiveBinaryTree<T>?) : Pair<RecursiveBinaryTree<T>?,RecursiveBinaryTree<T>?> {
        return Pair(elem,elem)
    }

    /*
    Adds the element to the binary tree dependent on the result of eval(elem,cand) where true puts it in the left
    subtree and false puts it in the right subtree.
     */
    fun add (cand: T) : Boolean {
        return when (eval(elem,cand)) {
            true -> {
                if (leaves.first == null) leaves = Pair(RecursiveBinaryTree(cand,eval), leaves.second)
                else (leaves.first)!!.add(cand)
                false
            }
            else -> {
                if (leaves.second == null) leaves = Pair(leaves.second, RecursiveBinaryTree(cand,eval))
                else (leaves.second)!!.add(cand)
                true
            }
        }
    }

    fun search (target: T) : Boolean {
        return when (eval(elem,target)) {
            true -> {
                if (leaves.first != null) (leaves.first)!!.search(target)
                else false
            }
            else -> {
                if (elem == target) true
                else {
                    if (leaves.second != null) (leaves.second)!!.search(target)
                    else false
                }
            }
        }
    }
}