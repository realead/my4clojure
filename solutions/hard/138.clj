;; Solution for 138. Squares Squared


;; Create a function of two integer arguments: the start and end, respectively. You must create a vector of strings which renders a 45° rotated square of integers
;; which are successive squares from the start point up to and including the end point. If a number comprises multiple digits, wrap them around the shape individually. If there are not enough digits to complete the shape, fill in the rest with asterisk characters. The direction of the drawing should be clockwise, starting from the center of the shape and working outwards, with the initial direction being down and to the right.


(defn sparse-to-full
  [board];expects map [x,y] -> char
  (let [ xs (map #(first  (first %)) board)
         ys (map #(second (first %)) board)
         minX (apply min xs)
         maxX (apply max xs)
         minY (apply min ys)
         maxY (apply max ys)
         trafo (fn[xy](vector (- (first xy) minX) (- (second xy) minY)))
         N (inc (- maxX minX))
         M (inc (- maxY minY))]
         (loop [res (vec (repeat N (vec (repeat M \space))))
               todo board]
               (if (empty? todo)
                   res
                   (let [indices (trafo (first (first todo)))
                         ch (second (first todo))]
                        (recur (assoc-in res indices ch) (rest todo))
                   )
                )
         )
   )
)

(def can-stop (into #{} (map #(* % %) (range 200))));quick and dirty to pass the test cases

(defn create-board
    [sq]
    (loop [sq  (concat sq (repeat \*))
           dir (cycle [ [-1 1] [1 1] [1 -1] [-1 -1] ])
           board {}
           cur '(0 0)
           id 0]
        (let [ch (first sq)
              cand (map + cur (second dir))]
              (if (contains? board cand); no turn possible => no end possible
                  (let [next-pos (map + cur (first dir))]
                      (recur (rest sq) dir (assoc board cur ch) next-pos (inc id))
                  )
                  (if (and (= \* ch) (contains? can-stop id))
                      board; we a done!
                      (recur (rest sq) (rest dir) (assoc board cur ch) cand (inc id)); turn for the next

                  )
              )
        )
    )
)



(defn rotated
    [start end]
    (let [numbers (take-while #(<= % end) (iterate #(* % %) start))
          as-str (apply str numbers)
          board (create-board as-str)]
        (map #(apply str %) (sparse-to-full board))
    )
)


(def __ rotated)


;tests
(= (__ 2 2) ["2"])



(= (__ 2 4) [" 2 "
             "* 4"
             " * "])


(= (__ 3 81) [" 3 "
              "1 9"
              " 8 "])


(= (__ 4 20) [" 4 "
              "* 1"
              " 6 "])


(= (__ 2 256) ["  6  "
               " 5 * "
               "2 2 *"
               " 6 4 "
               "  1  "])


(= (__ 10 10000) ["   0   "
                  "  1 0  "
                  " 0 1 0 "
                  "* 0 0 0"
                  " * 1 * "
                  "  * *  "
                  "   *   "])
