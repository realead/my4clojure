;;;; Solution for 95. tree on not to tree


;; Write a predicate which checks whether or not a given sequence represents a binary tree. Each node in the tree must have a value, a left child, and a right child.


(defn tree?
     [lst]
     (if (nil? lst)
        true
        (and (seq? lst) (= 3(count lst)) (tree? (second lst)) (tree? (last lst)))
     )
 )


(def __ tree?)


;tests
(= (__ '(:a (:b nil nil) nil))
   true)

(= (__ '(:a (:b nil nil)))
   false)

(= (__ [1 nil [2 [3 nil nil] [4 nil nil]]])
   true)

(= (__ [1 [2 nil nil] [3 nil nil] [4 nil nil]])
   false)

(= (__ [1 [2 [3 [4 nil nil] nil] nil] nil])
   true)

(= (__ [1 [2 [3 [4 false nil] nil] nil] nil])
   false)

(= (__ '(:a nil ()))
   false)

