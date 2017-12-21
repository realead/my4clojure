;;; Solution for 28. Flatten Sequence

;;Write a function which flattens a sequence.

(defn my-flatten
  [col]
  (reduce #(if (sequential? %2)
               (vec (concat %1 (my-flatten %2)))
               (conj %1 %2))
            [] col)
)

(my-flatten '((1 2) 3 [4 [5 6]]))


(def __ my-flatten)

;tests

(= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
(= (__ ["a" ["b"] "c"]) '("a" "b" "c"))
(= (__ '((((:a))))) '(:a))
