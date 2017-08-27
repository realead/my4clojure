;;;; Solution 44. Rotate Sequence


;; Write a function which can rotate a sequence in either direction.

(defn my-rotate
   [rot lst]
   (let [n       (count lst)
         offset  (mod rot n)]
        (take n (drop offset (cycle lst)))
    )
)

(def __ my-rotate)

;; tests

(= (__ 2 [1 2 3 4 5]) '(3 4 5 1 2))
(= (__ -2 [1 2 3 4 5]) '(4 5 1 2 3))
(= (__ 6 [1 2 3 4 5]) '(2 3 4 5 1))
(= (__ 1 '(:a :b :c)) '(:b :c :a))
(= (__ -4 '(:a :b :c)) '(:c :a :b))
