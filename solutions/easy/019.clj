;;; Solution for 19. Last Element


;Write a function which returns the last element in a sequence.
;special restriction: last

(defn my-last
    [col]
    (first (reverse col))
)

(def __ my-last)

;tests
(= (__ [1 2 3 4 5]) 5)
(= (__ '(5 4 3)) 3)
(= (__ ["b" "c" "d"]) "d")
