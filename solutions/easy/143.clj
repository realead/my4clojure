;;; Solution for 143. dot product

;;Create a function that computes the dot product of two sequences. You may assume that the vectors will have the same length.


(defn dot-product
   [x y]
   (reduce #(+ %1 (* (first %2) (last %2))) 0 (map vector x y) )
)


(def __ dot-product)

;tests
(= 0 (__ [0 1 0] [1 0 0]))
(= 3 (__ [1 1 1] [1 1 1]))
(= 32 (__ [1 2 3] [4 5 6]))
(= 256 (__ [2 5 6] [100 10 1]))
