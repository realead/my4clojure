;;;; Solution 92. Read roman numerals


;; Roman numerals are easy to recognize, but not everyone knows all the rules necessary to work with them. Write a function to parse a Roman-numeral string and return the number it represents.

;; You can assume that the input will be well-formed, in upper-case, and follow the subtractive principle. You don't need to handle any numbers greater than MMMCMXCIX (3999), the largest number representable with ordinary letters.


(def values {\I 1, \V 5, \X 10, \L 50, \C 100, \D 500, \M 1000})

(defn read-roman
  [roman]
  (loop [last-val 1
         result 0
         numbers (reverse roman)]
         (if (empty? numbers)
             result
             (let [val (values (first numbers))
                   new-sum (if (< val last-val) (- result val) (+ result val))]
                  (recur val new-sum (rest numbers))
             )
         )
  )
)

(def __ read-roman)


;;tests

(= 14 (__ "XIV"))
(= 827 (__ "DCCCXXVII"))
(= 3999 (__ "MMMCMXCIX"))
(= 48 (__ "XLVIII"))
