;;; Solution for 89. Graph tour


;;Starting with a graph you must write a function that returns true if it is possible to make a tour of the graph in which every edge is visited exactly once.

;;The graph is represented by a vector of tuples, where each tuple represents a single edge.

;;The rules are:

;;- You can start at any node.
;;- You must visit each edge exactly once.
;;- All edges are undirected.


;euler tour exists iff graph is connected and  number of nodes with odd degrees <=2
;reuse 091

(defn connected?
    [graph]
    (let [keys (set (flatten (seq graph)))
          el (first keys)]
          (loop [cones #{el}
                 diff 1]
                 (if (= 0 diff)
                     (= (count cones) (count keys)); connected <=> can reach all from first element
                     (let [add-cons (for [[s t] graph
                                          :when (or (contains? cones s) (contains? cones t))]
                                          [s t])
                           new-cons (into cones (flatten add-cons))]
                           (recur new-cons (- (count new-cons) (count cones)))
                     )
                 )
          )
    )
)

(defn count-odd
  [graph]
  (count (for [[x nr] (frequencies (flatten graph))
                :when (odd? nr)]
                x))
)

(defn euler?
    [graph]
    (and (connected? graph) (<= (count-odd graph) 2))
)

(def __ euler?)

;tests

(= true (__ [[:a :b]]))

(= false (__ [[:a :a] [:b :b]]))

(= false (__ [[:a :b] [:a :b] [:a :c] [:c :a]
               [:a :d] [:b :d] [:c :d]]))


(= true (__ [[1 2] [2 3] [3 4] [4 1]]))

(= true (__ [[:a :b] [:a :c] [:c :b] [:a :e]
              [:b :e] [:a :d] [:b :d] [:c :e]
              [:d :e] [:c :f] [:d :f]]))

(= false (__ [[1 2] [2 3] [2 4] [2 5]]))
