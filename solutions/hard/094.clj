;;;; Solution for 94. Game of Life


;; The game of life is a cellular automaton devised by mathematician John Conway.

;The 'board' consists of both live (#) and dead ( ) cells. Each cell interacts with its eight neighbours (horizontal, vertical, diagonal), and its next state is dependent on the following rules:

;1) Any live cell with fewer than two live neighbours dies, as if caused by under-population.
;2) Any live cell with two or three live neighbours lives on to the next generation.
;3) Any live cell with more than three live neighbours dies, as if by overcrowding.
;4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

;Write a function that accepts a board, and returns a board representing the next generation of cells.


(defn count-neighbors
    [x y board]
    (reduce + (for [i (range -1 2)
                 j (range -1 2)
                 :when (or (not= i 0) (not= j 0))]
                 (board [(+ i x) (+ j y)] 0)
                )
    )
)

(defn alive-next-round?
    [x y board]
    (let [neigh-cnt (count-neighbors x y board)]
        (if (= (board [x y]) 1)
            (or (= 2 neigh-cnt) (= 3 neigh-cnt))
            (= 3 neigh-cnt)
        )
    )
)

(defn parse-board
  [board]
  (let [n (count board)
        m (count (first board))]
       (into {} (for [i (range n)
              j (range m)
              :when (= \# (get (get board i) j))]
              [[i j] 1]))
  )
)


(defn next-board
  [board]
  (let [n (count board)
        m (count (first board))
        board-map (parse-board board)]
        (for [x (range n)]
             (apply str
                (for [y (range m)]
                  (if (alive-next-round? x y board-map)
                      \#
                      \space
                  )
                )
             )
        )
  )
)

(def __ next-board)


;tests

(= (__ ["      "
        " ##   "
        " ##   "
        "   ## "
        "   ## "
        "      "])
   ["      "
    " ##   "
    " #    "
    "    # "
    "   ## "
    "      "])



(= (__ ["     "
        "     "
        " ### "
        "     "
        "     "])
   ["     "
    "  #  "
    "  #  "
    "  #  "
    "     "])


(= (__ ["      "
        "      "
        "  ### "
        " ###  "
        "      "
        "      "])
   ["      "
    "   #  "
    " #  # "
    " #  # "
    "  #   "
    "      "])

