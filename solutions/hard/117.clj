;;;; Solution for 117. For Science!


;A mad scientist with tenure has created an experiment tracking mice in a maze. Several mazes have been randomly generated, and you've been tasked with writing a program to determine the mazes in which it's possible for the mouse to reach the cheesy endpoint. Write a function which accepts a maze in the form of a collection of rows, each row is a string where:

;    spaces represent areas where the mouse can walk freely
;    hashes (#) represent walls where the mouse can not walk
;    M represents the mouse's starting point
;    C represents the cheese which the mouse must reach

;The mouse is not allowed to travel diagonally in the maze (only up/down/left/right), nor can he escape the edge of the maze. Your function must return true iff the maze is solvable by the mouse.


(def neighbor-mask [[0 1] [1 0] [0 -1] [-1 0]])
(defn reachable?
    [board start goal]
    (loop [visited #{}
           todo [start]]
        (if (empty? todo)
            false
            (let [[x y] (peek todo)]
                (if (= [x y] goal)
                    true
                    (let [next-todos (for [[dx dy] neighbor-mask
                                           :let [nX (+ x dx)
                                                 nY (+ y dy)]
                                           :when (and (not (contains? visited [nX nY]))
                                                      (contains? #{\space \M \C} (get-in board [nX nY])))]
                                          [nX nY])]
                        (recur (into visited next-todos) (into (pop todo) next-todos))
                    )
                )
            )
        )
    )
)

(defn find-character
    [board ch]
    (first (for [[i row] (map-indexed vector board)
          [j CH] (map-indexed vector row)
          :when (= ch CH)]
          [i j])
    )
)

(find-character ["0A0", "1B1"] \B)

(defn way-exists?
     [board]
     (reachable? board (find-character board \M) (find-character board \C))
)
(def __ way-exists?)


;tests
(= true  (__ ["M   C"]))

(= false (__ ["M # C"]))

(= true  (__ ["#######"
              "#     #"
              "#  #  #"
              "#M # C#"
              "#######"]))

(= false (__ ["########"
              "#M  #  #"
              "#   #  #"
              "# # #  #"
              "#   #  #"
              "#  #   #"
              "#  # # #"
              "#  #   #"
              "#  #  C#"
              "########"]))

(= false (__ ["M     "
              "      "
              "      "
              "      "
              "    ##"
              "    #C"]))

(= true  (__ ["C######"
              " #     "
              " #   # "
              " #   #M"
              "     # "]))


(= true  (__ ["C# # # #"
              "        "
              "# # # # "
              "        "
              " # # # #"
              "        "
              "# # # #M"]))
