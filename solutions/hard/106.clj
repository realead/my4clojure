;;;; Solution for 106. Number Maze


;; Given a pair of numbers, the start and end point, find a path between the two using only three possible operations:

;;    double
;;    halve (odd numbers cannot be halved)
;;    add 2

;Find the shortest path through the "maze". Because there are multiple shortest paths, you must return the length of the shortest path, not the path itself.


(defn bfs
  [from to]
   (loop [cur from
          visited {cur 1}
          queue '()];;; list is not the right  data structure, whyt is clojure's way for fifo-queue?
       (let [d (visited cur)]
         (if (= cur to)
             d
             (let [next-numbers (for [f [#(* 2 %) #(if (odd? %) % (/ % 2)) #(+ % 2)]
                                     :let [n (f cur)]
                                     :when (not (contains? visited n))]
                                     n)
                   new-queue (apply list (concat queue next-numbers))
                   newly-found (interleave next-numbers (repeat (+ d 1)))
                   updated-visited (if (empty? newly-found) visited (apply assoc visited newly-found))]
                   (recur (peek new-queue) updated-visited (pop new-queue))
            )
        )
       )
   )
)

(def __ bfs)

(interleave [3 4 5] (repeat (+ 2 1)))

(for [f [#(* 2 %) #(+ % 2)]
                                     ]
                                     (f 4))
;;tests

(= 1 (__ 1 1))  ; 1
(= 3 (__ 3 12)) ; 3 6 12
(= 3 (__ 12 3)) ; 12 6 3
(= 3 (__ 5 9))  ; 5 7 9
(= 9 (__ 9 2))  ; 9 18 20 10 12 6 8 4 2
(= 5 (__ 9 12)) ; 9 11 22 24 12
