;;; Solution for 73.  Analyze a tic-tac-toe board

;;A tic-tac-toe board is represented by a two dimensional vector. X is represented by :x, O is represented by :o, and empty is represented by :e. A player wins by placing three Xs or three Os in a horizontal, vertical, or diagonal row. Write a function which analyzes a tic-tac-toe board and returns :x if X has won, :o if O has won, and nil if neither player has won.


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

(def __ who-won)


;tests

(= nil (__ [[:e :e :e]
            [:e :e :e]
            [:e :e :e]]))

(= :x (__ [[:x :e :o]
           [:x :e :e]
           [:x :e :o]]))

(= :o (__ [[:e :x :e]
           [:o :o :o]
           [:x :e :x]]))

(= nil (__ [[:x :e :o]
            [:x :x :e]
            [:o :x :o]]))

(= :x (__ [[:x :e :e]
           [:o :x :e]
           [:o :e :x]]))

(= :o (__ [[:x :e :o]
           [:x :o :e]
           [:o :e :x]]))


(= nil (__ [[:x :o :x]
            [:x :o :x]
            [:o :x :o]]))


