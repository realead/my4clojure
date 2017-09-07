;;;;; Solution for 74. Filter Perfect Squares

;; Given a string of comma separated integers, write a function which returns a new comma separated string that only contains the numbers which are perfect squares.


(defn filter-perfect-squares
  [comma-sep-list]
  (let [words (clojure.string/split comma-sep-list, #",")
        numbers (for [w words
                     :let [n (. Integer parseInt w)]
                     :when (some true? (for [x (range n)] (= (* x x) n)))]
                      n)]
        (clojure.string/join "," numbers)
   )
)


(def __ filter-perfect-squares)

; tests
(= (__ "4,5,6,7,8,9") "4,9")
(= (__ "15,16,25,36,37") "16,25,36")
