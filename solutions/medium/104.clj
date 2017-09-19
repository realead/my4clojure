;;; Solution for 104. Write Roman Numerals


;;This is the inverse of Problem 92, but much easier. Given an integer smaller than 4000, return the corresponding roman numeral in uppercase, adhering to the subtractive principle.



(defn write-roman
  [z]
  (cond
               (>= z 1000) (conj (write-roman (- z 1000)) \M)
               (>= z 900)  (conj (write-roman (- z 900))  \M \C)
               (>= z 500)  (conj (write-roman (- z 500))  \D)
               (>= z 400)  (conj (write-roman (- z 400))  \D \C)
               (>= z 100)  (conj (write-roman (- z 100))  \C)
               (>= z 90)   (conj (write-roman (- z 90))   \C \X)
               (>= z 50)   (conj (write-roman (- z 50))   \L)
               (>= z 40)   (conj (write-roman (- z 40))   \L \X)
               (>= z 10)   (conj (write-roman (- z 10))   \X)
               (= z 9)     (conj (write-roman (- z 90))   \X \I)
               (>= z 5)      (conj (write-roman (- z 5))    \V)
               (= z 4)     '(\I \V)
               (>= z 1)    (conj (write-roman (- z 1))    \I)
               :else () );z==0)]

)

(defn write-roman-sol
   [z]
    (apply str (write-roman z))
)

(def __ write-roman-sol)


;tests

(= "I" (__ 1))
(= "XXX" (__ 30))
(= "IV" (__ 4))
(= "CXL" (__ 140))
(= "DCCCXXVII" (__ 827))
(= "MMMCMXCIX" (__ 3999))
(= "XLVIII" (__ 48))
