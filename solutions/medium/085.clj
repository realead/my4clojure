;;;; Solution for 85. Power Set


;; Write a function which generates the power set of a given set. The power set of a set x is the set of all subsets of x, including the empty set and x itself.


(defn power-set
  ([st] (power-set st #{#{}}))
  ([st res]
    (if (empty? st)
       res
       (let [el (first st)]
         (recur (rest st) (into res (map #(conj %1 el) res)))
       )
    )
  )
)

(def __ power-set)
;tests

(__ #{1 :a})
(= (__ #{1 :a}) #{#{1 :a} #{:a} #{} #{1}})
(= (__ #{}) #{#{}})
(= (__ #{1 2 3})
   #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})
(= (count (__ (into #{} (range 10)))) 1024)
