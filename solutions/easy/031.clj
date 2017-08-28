(comment solution for 31. Pack a Sequence)

(defn my-pack
     ([lst] (my-pack lst []))
     ([lst res]
      (if (empty? lst) res
         ((fn consume
            [lst res ch col]
            (if (empty? lst) (conj res col)
              (if (not= (first lst) ch)
                  (my-pack lst (conj res col))
                  (recur (rest lst) res ch (conj col ch))
              )
            )
          )  lst res (first lst) [])
      )
    )
)


(= (my-pack [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (my-pack [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
(= (my-pack [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))
