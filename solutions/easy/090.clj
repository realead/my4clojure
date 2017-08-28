;;;; Solution for 90. Cartesian Product

;; Write a function which calculates the Cartesian product of two sets.


(defn my-cartesian-product
  ([set1 set2] (my-cartesian-product set1 set2 #{}))
  ([set1 set2 res]
      (if (empty? set1)
          res
          (let [el (first set1)]
               (recur (rest set1) set2
                    ((fn single-row
                         [el set2 res]
                         (if (empty? set2)
                             res
                             (recur el (rest set2) (conj res (conj []
el (first set2))))
                         )
                      ) el set2 res)
               )
          )
      )
  )
)


;tests

(= (my-cartesian-product #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})
   #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
     ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
     ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]})

(= (my-cartesian-product #{1 2 3} #{4 5})
   #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]})

(= 300 (count (my-cartesian-product (into #{} (range 10))
                  (into #{} (range 30)))))
