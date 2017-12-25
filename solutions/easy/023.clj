;;;; Solution for 23. Reverse a Sequence

;;; Write a function which reverses a sequence.
;; Special Restriction : rseq, reverse


;tests


(defn my-reverse
    [s]
    (if (empty? s)
        []
        (let [cur (my-reverse (rest s))]
            (conj cur (first s))
        )
    )
)

(def __ my-reverse)

;tests
(= (__ [1 2 3 4 5]) [5 4 3 2 1])
(= (__ (sorted-set 5 7 2 7)) '(7 5 2))
(= (__ [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]])
