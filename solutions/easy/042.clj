;;; Solution for 42. Factorial Fun


;; Write a function which calculates factorials.
(defn factorial
    [n]
    (apply * (range 1 (inc n)))
)

(def __ factorial)
;;tests
(= (__ 0) 1)
(= (__ 1) 1)
(= (__ 3) 6)
(= (__ 5) 120)
(= (__ 8) 40320)
