;;; solution for 96. beaty is symmetry


;;; Let us define a binary tree as "symmetric" if the left half of the tree is the mirror image of the right half of the tree. Write a predicate to determine whether or not a given binary tree is symmetric. (see To Tree, or not to Tree for a reminder on the tree representation we're using).


(defn tree-symmetric?
  ([tree] (if (nil? tree) true (tree-symmetric? (second tree) (last tree))))
  ([left right]
     (if (and (nil? left) (nil? right))
          true
          (and (= (first left) (first right))
               (tree-symmetric? (second left) (last right))
               (tree-symmetric? (last left) (second right)))
     )
  )
 )

(def __ tree-symmetric?)

;tests
(= (__ '(:a (:b nil nil) (:b nil nil))) true)
(= (__ '(:a (:b nil nil) nil)) false)
(= (__ '(:a (:b nil nil) (:c nil nil))) false)
(= (__ [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
          [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
   true)
(= (__ [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
          [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])
   false)
(= (__ [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
          [2 [3 nil [4 [6 nil nil] nil]] nil]])
    false)
