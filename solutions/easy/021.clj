;; Solution for 21. Nth Element


; Write a function which returns the Nth element from a sequence.
; special restriction: nth

(defn my-nth
    [col n]
    (loop [n n
           col col]
        (if (= 0 n)
            (first col)
            (recur (dec n) (rest col))
        )
    )
)

(def __ my-nth)


;;tests

(= (__ '(4 5 6 7) 2) 6)
(= (__ [:a :b :c] 0) :a)
(= (__ [1 2 3 4] 1) 2)
(= (__ '([1 2] [3 4] [5 6]) 2) [5 6])
