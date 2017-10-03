;;; Solution for  79. Triangle Minimal Path

;;Write a function which calculates the sum of the minimal path through a triangle.
;;The triangle is represented as a collection of vectors. The path should start at the top of the triangle and move to an adjacent number on the next row until the bottom of the triangle is reached.



(defn shortest
  [tree]
  (if (=  1 (count tree))
         (apply min (first tree))
         (let [left-side  (map + (first tree) (second tree))
               right-side (map + (first tree) (rest (second tree)))
               new-row (concat [(first left-side)]
                               (map min (rest left-side) right-side)
                               [(last right-side)])]
               (recur (cons new-row (drop 2 tree)))
         )
  )
)

(def __ shortest)
;tests

(= 7 (__ '([1]
          [2 4]
         [5 1 4]
        [2 3 4 5]))) ; 1->2->1->3


(= 20 (__ '([3]
           [2 4]
          [1 9 3]
         [9 9 2 4]
        [4 6 6 7 8]
       [5 7 3 5 1 4]))) ; 3->4->3->2->7->1
