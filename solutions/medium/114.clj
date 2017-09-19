;;; Solution for 114. Global take-while


;;take-while is great for filtering sequences, but it limited: you can only examine a single item of the sequence at a time. What if you need to keep track of some state as you go over the sequence?
;;Write a function which accepts an integer n, a predicate p, and a sequence. It should return a lazy sequence of items in the list up to, but not including, the nth item that satisfies the predicate.



(defn global-take-while
   [n pred col]
    (let [ [head tail] (split-with (complement pred) col)]
       (if (= n 1)
           head
           (lazy-seq (concat head [(first tail)] (global-take-while (dec n) pred (rest tail))))
       )
    )
)

(def __ global-take-while)


;tests
(= [2 3 5 7 11 13]
   (__ 4 #(= 2 (mod % 3))
         [2 3 5 7 11 13 17 19 23]))

(= ["this" "is" "a" "sentence"]
   (__ 3 #(some #{\i} %)
         ["this" "is" "a" "sentence" "i" "wrote"]))

(= ["this" "is"]
   (__ 1 #{"a"}
         ["this" "is" "a" "sentence" "i" "wrote"]))
