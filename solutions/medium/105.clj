;;;; Solution for 105. Identify keys and values


;; Given an input sequence of keywords and numbers, create a map such that each key in the map is a keyword, and the value is a sequence of all the numbers (if any) between it and the next keyword in the sequence.


(defn key-values
    ([col] (key-values col nil [] {}))
    ([col cur_key cur_col res]
      (if (empty? col)
         (if (nil? cur_key)
             res
             (assoc res cur_key cur_col))
         (let [el (first col)]
             (if (keyword? el)
                 (recur (rest col) el [] (if (nil? cur_key) res (assoc res cur_key cur_col)))
                 (recur (rest col) cur_key (conj cur_col el) res)
             )
         )
      )
    )
)

(def __ key-values)


;;test
(= {} (__ []))
(= {:a [1]} (__ [:a 1]))
(= {:a [1], :b [2]} (__ [:a 1, :b 2]))
(= {:a [1 2 3], :b [], :c [4]} (__ [:a 1 2 3 :b :c 4]))



