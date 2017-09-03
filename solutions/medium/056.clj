;;; Solution for 56. Find Distinct Items


;; Write a function which removes the duplicates from a sequence. Order of the items must be maintained.
;; Special restriction: distinct


(defn my-distinct
  ([col]  (my-distinct col #{} []))
  ([col seen res]
   (if (empty? col)
       res
       (let [el (first col)]
           (if (contains? seen el)
               (recur (rest col) seen res)
               (recur (rest col) (conj seen el) (conj res el))
           )
       )
   )
  )
)

(def __ my-distinct)

; tests

(= (__ [1 2 1 3 1 2 4]) [1 2 3 4])
(= (__ [:a :a :b :b :c :c]) [:a :b :c])
(= (__ '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
(= (__ (range 50)) (range 50))
