;;; Solution for 58. Function Composition


;; Write a function which allows you to create function compositions. The parameter list should take a variable number of functions, and create a function that applies them from right-to-left.
;; Special restriction: comp


(defn my-comp
  [& funs]
  (reduce (fn [res fun] (fn [& x] (res (apply fun x)))) identity funs)
)

(def __ my-comp)


;tests

((__ rest reverse) [1 2 3 4])
(= [3 2 1] ((__ rest reverse) [1 2 3 4]))
(= 5 ((__ (partial + 3) second) [1 2 3 4]))
(= true ((__ zero? #(mod % 8) +) 3 5 7 9))
(= "HELLO" ((__ #(.toUpperCase %) #(apply str %) take) 5 "hello world"))
