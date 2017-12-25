;;; solution for 22. Count a Sequence


;;Write a function which returns the total number of elements in a sequence.
;; special restriction: count

(defn my-count
    [col]
    (loop [res 0
           col col]
        (if (empty? col)
            res
            (recur (inc res) (rest col))
        )
    )
)

(def __ my-count)

;tests

(= (__ '(1 2 3 3 1)) 5)
(= (__ "Hello World") 11)
(= (__ [[1 2] [3 4] [5 6]]) 3)
(= (__ '(13)) 1)
(= (__ '(:a :b :c)) 3)
