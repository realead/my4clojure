;;; Solution for 53.

;;Given a vector of integers, find the longest consecutive sub-sequence of increasing numbers. If two sub-sequences have the same length, use the one that occurs first. An increasing sub-sequence must have a length of 2 or greater to qualify.

(defn picker
  [[best cur] el]
  (if (< (last cur) el)
       (let [cur (conj cur el)
             best (max-key count cur best)]; pick last in the case of equality
             (vector best cur)
        )
        (vector best [el])
   )
)

(defn pick-longest
    [v]
    (let [res-cand (first (reduce picker [[(first v)] [(first v)]] (rest v)))]
         (if (< (count res-cand) 2)
               []
               res-cand)
    )
)

(def __ pick-longest)

(__ [5 6 1 3 2 7])
;tests

(= (__ [1 0 1 2 3 0 4 5]) [0 1 2 3])
(= (__ [5 6 1 3 2 7]) [5 6])
(= (__ [2 3 3 4 5]) [3 4 5])
(= (__ [7 6 5 4]) [])
