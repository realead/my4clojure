;;;;; Solution for 81. Set Intersection

;;;; Write a function which returns the intersection of two sets. The intersection is the sub-set of items that each set has in common.

;; Special restriction: itersection


(defn my-intersect
  ([a b] (my-intersect a b #{}))
  ([a b res]
      (if (empty? a)
          res
          (let [el (first a)]
               (if (contains? b el)
                    (recur (rest a) b (conj res el))
                    (recur (rest a) b res)
               )
          )
      )
  )
)


;tests

(= (my-intersect #{0 1 2 3} #{2 3 4 5}) #{2 3})
(= (my-intersect #{0 1 2} #{3 4 5}) #{})
(= (my-intersect #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})
