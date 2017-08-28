;;;; Solution for 83. A Half-Truth

;; Write a function which takes a variable number of booleans. Your function should return true if some of the parameters are true, but not all of the parameters are true.
;; Otherwise your function should return false


(defn check [& args]
    (and (not (nil? (some true? args))) (not (nil? (some false? args))))
)




;; tests


(= false (check false false))
(= true (check true false))
(= false (check true))
(= true (check false true false))
(= false (check true true true))
(= true (check true true true false))
