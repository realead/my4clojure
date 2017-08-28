;;;; Solution for 49. Split a sequence


(defn my-split-at
      ([at lst] (my-split-at at lst []))
      ([at lst lhs]
         (if (= 0 at)
             (conj [] lhs lst)
             (recur (dec at) (rest lst) (conj lhs (first lst)))
         )
      )
 )



;;; tests:
(= (my-split-at 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
(= (my-split-at 1 [:a :b :c :d]) [[:a] [:b :c :d]])
(= (my-split-at 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])
