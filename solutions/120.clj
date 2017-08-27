;;;; solution for 120. Sum of square of digits


;;Write a function which takes a collection of integers as an argument. Return the count of how many elements are smaller than the sum of their squared component digits. For example: 10 is larger than 1 squared plus 0 squared; whereas 15 is smaller than 1 squared plus 5 squared.


(defn count-less-than
  [lst]
  ;reduce might be more efficient but for the sake of simplicity  I go with filter
  (count (filter (fn
                  [val]
                  (let [squared_sum ((fn squared [z res] (if (= 0 z)
                                             res
                                             (let [whole (quot z 10)
                                                   remaining (mod z 10)]
                                                  (squared whole (+ res (* remaining remaining)))
                                              )
                                          )
                                        ) val 0)]
                    (< val squared_sum)
                  )
                 ) lst))
)

(def __ count-less-than)

;tests

(= 8 (__ (range 10)))
(= 19 (__ (range 30)))
(= 50 (__ (range 100)))
(= 50 (__ (range 1000)))
