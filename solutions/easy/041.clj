;;;; Solution for 41. Drop Every Nth Item

(defn drop-nth
    ([lst n] (drop-nth lst n [] n))
    ([lst n res current]
        (if (empty? lst) res
            (if (= 1 current)
                (recur (rest lst) n res n) ;; drop this element
                (recur (rest lst) n (conj res (first lst)) (dec current))
            )
          )
     )
)


;;;test cases
(= (drop-nth [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
(= (drop-nth [:a :b :c :d :e :f] 2) [:a :c :e])
(= (drop-nth [1 2 3 4 5 6] 4) [1 2 3 5 6])

