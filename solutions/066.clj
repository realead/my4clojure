;;; Solution for 66. Greatest Common Divisor

;; Given two integers, write a function which returns the greatest common divisor.


(defn gcd
    [a b]
    (if (= 0 b)
        a
        (recur b (mod a b))
    )
)


; tests

(= (gcd 2 4) 2)
(= (gcd 10 5) 5)
(= (gcd 5 7) 1)
(= (gcd 1023 858) 33)
