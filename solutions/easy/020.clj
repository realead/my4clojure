;;;; Solution for 20. Penultimate Element


; Write a function which returns the second to last element from a sequence.


(defn penultimate
    [col]
    (last (butlast col))
)

(def __ penultimate)
;tests

(= (__ (list 1 2 3 4 5)) 4)
(= (__ ["a" "b" "c"]) "b")
(= (__ [[1 2] [3 4]]) [1 2])
