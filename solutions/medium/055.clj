;;;;; Solution for 55. Count Occurence

;;  Write a function which returns a map containing the number of occurences of each distinct item in a sequence.
;; special restriction: frequencies

(defn my-frequencies
  [col]
  (reduce (fn cnt-entry
             [dict el]
             (assoc dict el (inc (dict el 0)))
           ) {} col)
)

(def __ my-frequencies)

;tests

(= (__ [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})
(= (__ [:b :a :b :a :b]) {:a 2, :b 3})
(= (__ '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})
