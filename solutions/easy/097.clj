;;;; Solution for 97. Pacal's Triangle

;; Pascal's triangle is a triangle of numbers computed using the following rules:
;;
;; - The first row is 1.
;; - Each successive row is computed by adding together adjacent numbers in the row above, and adding a 1 to the beginning and end of the row.
;;
;; Write a function which returns the nth row of Pascal's Triangle.


(defn my-pascal-triangle-line
  ([n] (my-pascal-triangle-line 1 (dec n) 1 [1]))
  ([cur top down res]
   (if (= top 0)
       res
       (let [entry (/ (* cur top) down)]
          (recur entry (dec top) (inc down) (conj res entry))
       )
   )
  )
)

(def __ my-pascal-triangle-line)


;tests
(= (__ 1) [1])
(= (map __ (range 1 6))
   [     [1]
        [1 1]
       [1 2 1]
      [1 3 3 1]
     [1 4 6 4 1]])

(= (__ 11)
   [1 10 45 120 210 252 210 120 45 10 1])
