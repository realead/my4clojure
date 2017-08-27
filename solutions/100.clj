;;; Solition for 100. Least Common Multiple


;; Write a function which calculates the least common multiple. Your function should accept a variable number of positive integers or ratios.


;probably not the way it should be done: try numbers first_number*1, first_number*2.... until one found that works
;but it is totaly unclear to me how it should work otherwise with ratios...

(defn my-lcm
       [& args]
       ((fn [lst n]
          (let [val (* (first lst) n)]
              (if (every? true? (map #(integer? (/ val %1)) lst))
                    val
                    (recur lst (inc n))
              )
          )
         ) args 1)
)


(integer?  (/ 5 6))
;tests
(== (__ 2 3) 6)
(== (__ 5 3 7) 105)
(== (__ 1/3 2/5) 2)
(== (__ 3/4 1/6) 3/2)
(== (__ 7 5/7 2 3/5) 210)
