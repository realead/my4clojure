;;;; Solution for 86. Happy Numbers

;;Happy numbers are positive integers that follow a particular formula: take each individual digit, square it, and then sum the squares to get a new number. Repeat with the new number and eventually, you might get to a number whose squared sum is 1. This is a happy number. An unhappy number (or sad number) is one that loops endlessly. Write a function that determines if a number is happy or not.


(defn happy-number?
   ([n] (happy-number? n #{}))
   ([n seen]
    (cond (= 1 n) true
          (contains? seen n) false ;we are on a cycle
          :else  (let [next (loop [z n
                          s 0]
                          (if (== z 0)
                              s
                              (recur (quot z 10) (let [r (mod z 10)] (+ s (* r r))))
                          ))]
                      (recur next (conj seen n))
                 )
    )
   )
)


(def __ happy-number?)

;; tests
(= (__ 7) true)
(= (__ 986543210) true)
(= (__ 2) false)
(= (__ 3) false)
