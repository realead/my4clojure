;;;; Solution for 54.  Partion a sequence


;; Write a function which returns a sequence of lists of x items each. Lists of less than x items should not be returned.
;; Special restrictions: partition, partition-all

(defn my-partition
  ([n col] (my-partition n col [] []))
  ([n col res cand]
     (if (empty? col)
          res
          (let [next_cand (conj cand (first col))]
             (if (=  n (count next_cand))
              (recur n (rest col) (conj res next_cand) [])
              (recur n (rest col) res next_cand)
             )
          )
     )
  )
 )


(def __ my-partition)

;tests

(= (__ 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))
(= (__ 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))
(= (__ 3 (range 8)) '((0 1 2) (3 4 5)))

