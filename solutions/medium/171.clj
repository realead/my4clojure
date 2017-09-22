;;; Solution for 171. Intervals


;; Write a function that takes a sequence of integers and returns a sequence of "intervals". Each interval is a a vector of two integers, start and end, such that all integers between start and end (inclusive) are contained in the input sequence.



(defn intervals
  [ints]
    (if (empty? ints)
        []
        (let [sorted (sort ints)
              head (first sorted)
              tail (rest sorted)]
              (reduce #(let [[start end] (last %1)] (if (< (inc end) %2) (conj %1 [%2 %2]) (conj (pop %1) [start %2]))) [[head head]] tail)
        )
    )
)

(def __ intervals)

(__ [1 1 1 1 1 1])
;;tests

(= (__ [1 2 3]) [[1 3]])
(= (__ [10 9 8 1 2 3]) [[1 3] [8 10]])
(= (__ [1 1 1 1 1 1 1]) [[1 1]])
(= (__ []) [])
(= (__ [19 4 17 1 3 10 2 13 13 2 16 4 2 15 13 9 6 14 2 11])
       [[1 4] [6 6] [9 11] [13 17] [19 19]])
