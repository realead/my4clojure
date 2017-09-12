;;;; Solution for 075. Euler's Totient Function


;; Two numbers are coprime if their greatest common divisor equals 1. Euler's totient function f(x) is defined as the number of positive integers less than x which are coprime to x. The special case f(1) equals 1. Write a function which calculates Euler's totient function.


(defn totient
  [n]
  (if (= 1 n)
     1
     (let [gcd (fn [a b] (if (= 0  b)
                            a
                            (recur b (mod a b))))]
         (count (for [x (range 1 n)
                     :when (= 1 (gcd n x))]
                      x))
     )
  )
)

(def __ totient)



;;tests

(= (__ 1) 1)
(= (__ 10) (count '(1 3 7 9)) 4)
(= (__ 40) 16)
(= (__ 99) 60)
