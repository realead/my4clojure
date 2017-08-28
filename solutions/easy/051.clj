;;; solution for 51. Advanced Destructing


;; __ = [1 2 3 4 5]


;; tests

(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] [1 2 3 4 5]] [a b c d]))

