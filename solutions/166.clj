;;; Solution for 166. Comparison

;;For any orderable data type it's possible to derive all of the basic comparison operations (<, ≤, =, ≠, ≥, and >) from a single operation (any operator but = or ≠ will work). Write a function that takes three arguments, a less than operator for the data and two items to compare. The function should return a keyword describing the relationship between the two items. The keywords for the relationship between x and y are as follows:

;    x = y → :eq
;    x > y → :gt
;    x < y → :lt



(defn my-compare
     [less_op x y]
     (if (less_op x y)
            :lt
         (if (less_op y x) :gt :eq)
     )
)

;tests

(= :gt (my-compare < 5 1))
(= :eq (my-compare (fn [x y] (< (count x) (count y))) "pear" "plum"))
(= :lt (my-compare (fn [x y] (< (mod x 5) (mod y 5))) 21 3))
(= :gt (my-compare > 0 2))


