;;;;; Solution for 158. Decurry


;; Write a function that accepts a curried function of unknown arity n. Return an equivalent function of n arguments.


(defn my-decurry
    [f]
    (partial
        (fn decurried
            [res & args]
            (if (empty? args)
                 res
                 (recur (res (first args)) (rest args))
            )
        ) f)
)

(def __ my-decurry)


;tests:

(= 10 ((__ (fn [a]
             (fn [b]
               (fn [c]
                 (fn [d]
                   (+ a b c d))))))
       1 2 3 4))


(= 24 ((__ (fn [a]
             (fn [b]
               (fn [c]
                 (fn [d]
                   (* a b c d))))))
       1 2 3 4))


(= 25 ((__ (fn [a]
             (fn [b]
               (* a b))))
       5 5))
