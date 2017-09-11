;;; Solution for 80. Perfect Numbers


;; A number is "perfect" if the sum of its divisors equal the number itself. 6 is a perfect number because 1+2+3=6. Write a function which returns true for perfect numbers and false otherwise.


(defn perfect-number?
  [n]
  (= n (reduce #(if (= 0 (mod n %2)) (+ %2 %1) %1) (range 1 n)))
)

(def __ perfect-number?)

;tests

(= (__ 6) true)
(= (__ 7) false)
(= (__ 496) true)
(= (__ 500) false)
(= (__ 8128) true)

