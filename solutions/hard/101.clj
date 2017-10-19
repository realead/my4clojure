;;;; Solution for 101. Levenshtein Distance

;; Given two sequences x and y, calculate the Levenshtein distance of x and y, i. e. the minimum number of edits needed to transform x into y. The allowed edits are:

;;- insert a single item
;;- delete a single item
;;- replace a single item with another item

;;WARNING: Some of the test cases may timeout if you write an inefficient solution!

(defn lev
  [f s mem]
  (let [mkey [(count f) (count s)]]
  (cond (contains? mem mkey)  [(mem mkey) mem]
        (empty? f) [(count s) mem]
        (empty? s) [(count f) mem]
        :else
          (let [cost (if (= (first f) (first s)) 0 1)
            [both mem] (lev (rest f) (rest s) mem)
            [ins_f mem] (lev f (rest s) mem)
            [ins_s mem] (lev (rest f) s mem)
            m (min (inc ins_f) (inc ins_s) (+ cost both))]
            [m (assoc mem mkey m)]
          )
  )
  )
)

(defn lev-dist
    [s f]
    (first (lev s f {[() ()] 0}))
)


(def __ lev-dist)

;tests

(= (__ "kitten" "sitting") 3)
(= (__ "closure" "clojure") (__ "clojure" "closure") 1)
(= (__ "xyx" "xyyyx") 2)
(= (__ "" "123456") 6)
(= (__ "Clojure" "Clojure") (__ "" "") (__ [] []) 0)
(= (__ [1 2 3 4] [0 2 3 4 5]) 2)
(= (__ '(:a :b :c :d) '(:a :d)) 2)
(= (__ "ttttattttctg" "tcaaccctaccat") 10)
(= (__ "gaattctaatctc" "caaacaaaaaattt") 9)
