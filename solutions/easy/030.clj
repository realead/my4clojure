;;; Solution for 30. Compress a Sequence


;; Write a function which removes consecutive duplicates from a sequence.


(defn my-compress
    [col]
    (reduce #(if (or (empty? %1) (not= (last %1) %2)) (conj %1 %2) %1) [] col)
)

(def __ my-compress)

;tests
(= (apply str (__ "Leeeeeerrroyyy")) "Leroy")
(= (__ [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
(= (__ [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))
