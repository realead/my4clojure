;;; Solution for 34. Implement range

;;Write a function which creates a list of all integers in a given range.
;; Special restriction: range


; not lazy:
(defn my-range
    [from to]
    (loop [cur from
           res []]
          (if (= cur to)
              res
              (recur (inc cur) (conj res cur))
          )
    )
)

(def __ my-range)
;tests

(= (__ 1 4) '(1 2 3))
(= (__ -2 2) '(-2 -1 0 1))
(= (__ 5 8) '(5 6 7))
