;;;; Solution for 60. Sequence Reduction

;;Write a function which behaves like reduce, but returns each intermediate value of the reduction. Your function must accept either two or three arguments, and the return sequence must be lazy.

;; Special restriction: reductions


(defn my-reductions
  ([fun col] (my-reductions fun (fun (first col)) (rest col)))
  ([fun start col]
     (if (empty? col)
         [start]
         (lazy-seq
            (cons start (my-reductions fun (fun start (first col)) (rest col)))
         )
      )
  )
)

(def __ my-reductions)



;;tests

(= (take 5 (__ + (range))) [0 1 3 6 10])
(= (__ conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])
(= (last (__ * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120)
