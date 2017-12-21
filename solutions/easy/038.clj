;;; Solution for 38. Maximum value

;;; Write a function which takes a variable number of parameters and returns the maximum value.
; special restriction max, max-key

(defn my-max
    [ x & args]
    (reduce #(if (> %2 %1) %2 %1) x args)
)

(def __ my-max)

;tests
(= (__ 1 8 3 4) 8)
(= (__ 30 20) 30)
(= (__ 45 67 11) 67)
