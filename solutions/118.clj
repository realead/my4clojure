;;;; Solution for 118. Re-implement map


;; Map is one of the core elements of a functional programming language. Given a function f and an input sequence s, return a lazy sequence of (f x) for each element x in s.

;; Special Restrictions map map-indexed mapcat for


;;reduce is wrong because it is not lazy:
;; (defn my-map [fun lst]  (reduce #(conj %1 (fun %2)) [] lst) )


(defn my-map
   ([fun lst]
    (if (empty? lst)
        nil
        (lazy-seq (cons (fun (first lst))
                  (my-map fun (rest lst))))
    )
   )
)

(def __ my-map)

; tests

(= [3 4 5 6 7]
   (__ inc [2 3 4 5 6]))


(= (repeat 10 nil)

   (__ (fn [_] nil) (range 10)))


(= [1000000 1000001]

   (->> (__ inc (range))

        (drop (dec 1000000))

        (take 2)))
