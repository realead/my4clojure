;;;; Solution for 147. Pascal's Trapezoid


;; Write a function that, for any given input vector of numbers, returns an infinite lazy sequence of vectors, where each next one is constructed from the previous following the rules used in Pascal's Triangle. For example, for [3 1 2], the next row is [3 4 3 2].

;; Beware of arithmetic overflow! In clojure (since version 1.3 in 2011), if you use an arithmetic operator like + and the result is too large to fit into a 64-bit integer, an exception is thrown.
;; You can use +' to indicate that you would rather overflow into Clojure's slower, arbitrary-precision bigint.


(defn pascal-trapeziod
   ([lst] (pascal-trapeziod lst [] 0))
   ([lst res last_value]
      (if (empty? lst)
          (conj res last_value)
          (let [next_value (first lst)
                next_entry (+' last_value next_value)]
                (recur (rest lst) (conj res next_entry) next_value)
          )
      )
   )
)


; inlining the above function, so it can be used on-line
(defn lazy-pascal-trapezoid
   [lst]
   (let [ next-pascal-line
                    (fn
                      [lst res last_value]
                          (if (empty? lst)
                              (conj res last_value)
                              (let [next_value (first lst)
                                    next_entry (+' last_value next_value)]
                                    (recur (rest lst) (conj res next_entry) next_value)
                              )
                          )
                    )

          val (next-pascal-line lst [] 0)]
        (lazy-seq (cons lst (lazy-pascal-trapezoid val)))
   )
)



(def __ lazy-pascal-trapezoid)


;tests
(= (second (__ [2 3 2])) [2 5 5 2])
(= (take 5 (__ [1])) [[1] [1 1] [1 2 1] [1 3 3 1] [1 4 6 4 1]])
(= (take 2 (__ [3 1 2])) [[3 1 2] [3 4 3 2]])
(= (take 100 (__ [2 4 2])) (rest (take 101 (__ [2 2]))))
