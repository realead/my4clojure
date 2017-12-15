;;;; Solution for  152. Latin Square Slicing


(defn rows-latin?
    [square]
    (and (apply = (count square) (map count (map set square)))
         (apply = (map #(into #{} %) square))
    )
)

(rows-latin? [[1]])
(rows-latin? [[1] [2]])
(rows-latin? [[2 1] [1 2]])
(rows-latin? [[1 3] [2 1]])
(rows-latin? [[][1 2]])
(rows-latin? [[1 2 1] [2 1 2] [1 2 1]])

;;;

(defn latin?
    [square]
    (and (rows-latin? square)
         (rows-latin? (apply map vector square)))

)

(latin? [[1 2] [2 1]])
(latin? [[1 2] [1 2]])
(latin? [[1 2 1] [2 1 2] [1 2 1]])

;;;

(defn possible-sub-rows
    [start L N row]
    (let [n (count row)
          diff (- N n)
          begin (max 0 (- start diff))
          end   (inc (min start (- n L)))]
          (for [s (range begin end)]
                (subvec row s (+ s L))
          )
    )
)

(possible-sub-rows 2 2 6 [0 1 2 3 4 5])
(possible-sub-rows 2 2 6 [0 1 2 3 4])
(possible-sub-rows 2 2 6 [0 1 2 3])

;;

(defn cartesian [colls]
  (if (empty? colls)
    '(())
    (for [x (first colls)
          more (cartesian (rest colls))]
         (conj more x)
    )
  )
)


(cartesian [[1 2] [3] [4 5]])
(cartesian [[1 2] []])


;;


(defn enumerate-subsquares;;all subsquare of dimension (count square)
    [square M]
    (let [N (count square)]
          (apply concat
               (for [start (range (inc (- M N)))]
                  (cartesian (map #(possible-sub-rows start N M %) square))
               )
          )
    )
)


(enumerate-subsquares [[1 2] [2 3 4]] 3)

;;;

(defn enumerate-subsquares-with-dim-k
    [square k]
    (let [N (count square)
          end (inc (- N k))
          M (apply max (map count square))]
         (apply concat
              (for [start (range end)]
                  (enumerate-subsquares (subvec square start (+ start k)) M)
              )
         )
    )
)

(println (enumerate-subsquares-with-dim-k [[1 2] [2 3 4] [5 6 7]] 2))
(enumerate-subsquares-with-dim-k '[[A B C D]
         [A C D B]
         [B A D C]
         [D C A B]] 3)
;;

(defn latin-frequencies
    [square]
    (let [N (count square)]
        (into {}
             (for [k (range 2 (inc N))
                   :let [entry (count (filter latin? (distinct (enumerate-subsquares-with-dim-k square k))))]
                   :when (> entry 0)]
                  [k entry]
             )
        )
    )
)

(latin-frequencies '[[A B C D]
         [A C D B]
         [B A D C]
         [D C A B]])


(def bad-example
       [[8 6 7 3 2 5 1 4]
        [6 8 3 7]
        [7 3 8 6]
        [3 7 6 8 1 4 5 2]
              [1 8 5 2 4]
              [8 1 2 4 5]])

(latin-frequencies bad-example)

(println (filter latin? (distinct (enumerate-subsquares-with-dim-k bad-example 2))))


(def __ latin-frequencies)
;tests:


(= (__ '[[A B C D]
         [A C D B]
         [B A D C]
         [D C A B]])
   {})

(= (__ '[[A B C D E F]
         [B C D E F A]
         [C D E F A B]
         [D E F A B C]
         [E F A B C D]
         [F A B C D E]])
   {6 1})

(= (__ '[[A B C D]
         [B A D C]
         [D C B A]
         [C D A B]])
   {4 1, 2 4})

(= (__ '[[B D A C B]
         [D A B C A]
         [A B C A B]
         [B C A B C]
         [A D B C A]])
   {3 3})

(= (__ [  [2 4 6 3]
        [3 4 6 2]
          [6 2 4]  ])
   {})


(= (__ [[1]
        [1 2 1 2]
        [2 1 2 1]
        [1 2 1 2]
        []       ])
   {2 2})

(= (__ [[3 1 2]
        [1 2 3 1 3 4]
        [2 3 1 3]    ])
   {3 1, 2 2})

(= (__ [[8 6 7 3 2 5 1 4]
        [6 8 3 7]
        [7 3 8 6]
        [3 7 6 8 1 4 5 2]
              [1 8 5 2 4]
              [8 1 2 4 5]])
   {4 1, 3 1, 2 7})
