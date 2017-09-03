;;;;; Solution for 50. Split by type

;; Write a function which takes a sequence consisting of items with different types and splits them up into a set of homogeneous sub-sequences. The internal order of each sub-sequence should be maintained, but the sub-sequences themselves can be returned in any order (this is why 'set' is used in the test cases).


(defn my-split-by-type
   [lst]
   (let [groups ((fn collect-by-type
                   [col dict]
                   (if (empty? col)
                        dict
                       (let [el (first col)
                             tp (type el)]
                          (recur (rest col) (assoc dict tp (conj (dict tp []) el)))
                       )
                    )
                 ) lst {})]
     (into #{} (for [[key vals] groups] vals))
  )
)


(def __ my-split-by-type)


;tests

(= (set (__ [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})
(= (set (__ [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]})
(= (set (__ [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})
