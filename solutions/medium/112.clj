;; Solution for 112. Sequs Horribilis



;; Create a function which takes an integer and a nested collection of integers as arguments. Analyze the elements of the input collection and return a sequence which maintains the nested structure,
;; and which includes all elements starting from the head whose sum is less than or equal to the input integer.

;;N.B. It is not clear from description but from examples, that the sequence should stop after first element with sum > n

(defn seq-hor
   [col mask]
    (if (empty? col)
       ['(), nil]
       (let [el (first col)]
         (if (sequential? el)
             (let [[new_el new_mask] (seq-hor el mask)
                   [new_tail new_mask] (seq-hor (rest col) new_mask)]
                  [(conj new_tail new_el) new_mask]
             )
             (if (first mask)
                 (let [[tail new_mask] (seq-hor (rest col) (rest mask))]
                     [(conj tail el) new_mask]
                 )
                 ['(), nil];stop, first element with sum > n
             )
         )
       )
    )
)

(defn do-seq-hor
  [n col]
  (let [[res mask] (seq-hor col  (map #(<= % n) (reductions + (flatten col))))]
      res
  )
)


(def __ do-seq-hor)


(__ 0 [1 2 [3 [4 5] 6] 7])

;tests

(=  (__ 10 [1 2 [3 [4 5] 6] 7])
   '(1 2 (3 (4))))

(=  (__ 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11])
   '(1 2 (3 (4 (5 (6 (7)))))))

(=  (__ 9 (range))
   '(0 1 2 3))

(=  (__ 1 [[[[[1]]]]])
   '(((((1))))))

(=  (__ 0 [1 2 [3 [4 5] 6] 7])
   '())

(=  (__ 0 [0 0 [0 [0]]])
   '(0 0 (0 (0))))

(=  (__ 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]])
   '(-10 (1 (2 3 (4)))))
