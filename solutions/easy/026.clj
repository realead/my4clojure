;;;; Solution for 26. Fibonacci Sequence

; Write a function which returns the first X fibonacci numbers.

(defn fib
    [n]
    (loop [a 1
           b 1
           c 2
           res [1]]
           (if (= c n)
               (conj res b)
               (recur b (+ a b) (inc c) (conj res b))
           )
    )
)

(def __ fib)

;tests

(= (__ 3) '(1 1 2))
(= (__ 6) '(1 1 2 3 5 8))
(= (__ 8) '(1 1 2 3 5 8 13 21))
