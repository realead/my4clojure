;;;; Solution for 99. Product Digits

;;Write a function which multiplies two numbers and returns the result as a sequence of its digits.

(defn product-digits
  [a b]
  ( let [prod (* a b)]
    (if (= 0 prod)
        '(0);the only one digit
      ((fn list-of-digits
        [z, lst]
        (if (= 0 z)
            lst
            (recur (quot z 10) (conj lst (mod z 10)))
        )
       ) prod '())
    )
  )
)

;;tests

(= (product-digits 1 0) [0])
(= (product-digits 1 1) [1])
(= (product-digits 99 9) [8 9 1])
(= (product-digits 999 99) [9 8 9 0 1])
