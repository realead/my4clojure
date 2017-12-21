;;; Solution for 40. Interpose a Seq


;;Write a function which separates the items of a sequence by an arbitrary value.
; special restriction: interpose

(defn my-interpose
   [v col]
   (flatten (cons (first col) (map #(vector v %1) (rest col))))
)

(my-interpose 0 [1 2 3])

(def __ my-interpose)
;;test not run

(= (__ 0 [1 2 3]) [1 0 2 0 3])


(= (apply str (__ ", " ["one" "two" "three"])) "one, two, three")



(= (__ :z [:a :b :c :d]) [:a :z :b :z :c :z :d])
