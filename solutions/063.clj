;;;;; Solution for 63. Group a sequence


;; Given a function f and a sequence s, write a function which returns a map. The keys should be the values of f applied to each item in s. The value at each key should be a vector of corresponding items in the order they appear in s.
;; special restriction: group-by



(defn my-group-by
      [fun lst]
      ((fn insert-key-value-pairs-in-dict
           [lst dict]
           (if (empty? lst)
                dict
                (let [key (first lst)
                      val (second lst)
                      val-lst (conj (dict (first lst) []) val)]
                      (recur (drop 2 lst) (assoc dict key val-lst))
                )
            )
        ) (interleave (map fun lst) lst) {})
  )




(def lst [1 2 3 4 5])
(def fun #(> % 2))
(def r (interleave (map fun lst) lst))
(drop 2 r)

(my-group-by #(> % 5) [1 3 6 8])
;tests

(= (my-group-by #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})
(= (my-group-by #(apply / %) [[1 2] [2 4] [4 6] [3 6]])  {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})
(= (my-group-by count [[1] [1 2] [3] [1 2 3] [2 3]])  {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})
