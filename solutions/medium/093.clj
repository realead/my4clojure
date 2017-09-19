;;; Solution for 93. Partially Flatten  a Sequence



;;;  Write a function which flattens any nested combination of sequential things (lists, vectors, etc.),
;;;  but maintains the lowest level sequential items. The result should be a sequence of sequences with only one level of nesting.


(defn partially-flatten
    [col]
    (if (empty? col)
        []
        (let [el (first col)
              head (if (and (sequential? el) (sequential? (first el)))
                         (partially-flatten el)
                         [el]
                   )
              tail (rest col)]
              (if (empty? tail)
                  head
                  (apply conj head (partially-flatten (rest col)))
              )
        )
    )
)

(def __ partially-flatten)

;tests

(= (__ [["Do"] ["Nothing"]])
   [["Do"] ["Nothing"]])

(= (__ [[[[:a :b]]] [[:c :d]] [:e :f]])
   [[:a :b] [:c :d] [:e :f]])


(= (__ '((1 2)((3 4)((((5 6)))))))
   '((1 2)(3 4)(5 6)))
