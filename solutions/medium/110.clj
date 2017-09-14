;;; Solution for 110. Sequence of pronunciations


;;  Write a function that returns a lazy sequence of "pronunciations" of a sequence of numbers. A pronunciation of each element in the sequence consists of the number of repeating identical numbers and the number itself. For example, [1 1] is pronounced as [2 1] ("two ones"), which in turn is pronounced as [1 2 1 1] ("one two, one one").

;;Your function should accept an initial sequence of numbers, and return an infinite lazy sequence of pronunciations, each element being a pronunciation of the previous element.



(defn next-prons
  ([vals] (next-prons (rest vals) (first vals) 1 []))
  ([vals cur_val cur_cnt res]
    (if (empty? vals)
        (conj res cur_cnt cur_val )
        (let [el (first vals)]
           (if (= el cur_val)
               (recur (rest vals) cur_val (inc cur_cnt) res)
               (recur (rest vals) el 1 (conj res cur_cnt cur_val ))
           )
        )
    )
  )
)

(defn my-prons
  [vals]
  (let [next (next-prons vals)]
     (lazy-seq (cons next (my-prons next)))
  )
)

(def __ my-prons)


;;tests

(= [[1 1] [2 1] [1 2 1 1]] (take 3 (__ [1])))
(= [3 1 2 4] (first (__ [1 1 1 4 4])))
(= [1 1 1 3 2 1 3 2 1 1] (nth (__ [1]) 6))
(= 338 (count (nth (__ [3 2]) 15)))
