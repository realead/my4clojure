;;;; Solution for 45.Intor to Iterate

;;The iterate function can be used to produce an infinite lazy sequence.

(def __ [1 4 7 10 13])

;tests

(= __ (take 5 (iterate #(+ 3 %) 1)))
