;;; Solution for 119. Win at Tic-Tac-Two

;As in Problem 73, a tic-tac-toe board is represented by a two dimensional vector. X is represented by :x, O is represented by :o, and empty is represented by :e. Create a function that accepts a game piece and board as arguments, and returns a set (possibly empty) of all valid board placements of the game piece which would result in an immediate win.

;Board coordinates should be as in calls to get-in. For example, [0 1] is the topmost row, center position.

;reusing code from 73.

(defn who-won
   [board]
   (let [all  (concat (map set board)                ;rows
                      (map set (apply map vector board))   ;cols
                      (map set [[(first (first board)) (second (second board)) (last (last board))] ;diags
                                [(first (last board)) (second (second board)) (last (first board))]]))
         on-board (into #{} all)]
         (cond
             (contains? on-board #{:x}) :x
             (contains? on-board #{:o}) :o
             :else nil
         )
    )
)


(defn win-move?
    [board move el]
    (and (= :e (get-in board move))
         (not (nil? (who-won (assoc-in board move el))))
    )
)

(defn win-moves
    [el board]
         (into #{} (for [x (range 3)
                      y (range 3)
                      :let [move [x y]]
                      :when (win-move? board move el)]
                      move)
        )
)

(def __ win-moves)


;tests

(= (__ :x [[:o :e :e]
           [:o :x :o]
           [:x :x :e]])
   #{[2 2] [0 1] [0 2]})


(= (__ :x [[:x :o :o]
           [:x :x :e]
           [:e :o :e]])
   #{[2 2] [1 2] [2 0]})


(= (__ :x [[:x :e :x]
           [:o :x :o]
           [:e :o :e]])
   #{[2 2] [0 1] [2 0]})


(= (__ :x [[:x :x :o]
           [:e :e :e]
           [:e :e :e]])
   #{})


(= (__ :o [[:x :x :o]
           [:o :e :o]
           [:x :e :e]])
   #{[2 2] [1 1]})
