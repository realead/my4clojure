;;;;; Solution for 91. Graph connectivity



;;Given a graph, determine whether the graph is connected. A connected graph is such that a path exists between any two given nodes.
;;-Your function must return true if the graph is connected and false otherwise.
;;-You will be given a set of tuples representing the edges of a graph. Each member of a tuple being a vertex/node in the graph.
;;-Each edge is undirected (can be traversed either direction).



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

(def __ connected?)


; tests

(= true (__ #{[:a :a]}))
(= true (__ #{[:a :b]}))
(= false (__ #{[1 2] [2 3] [3 1]
               [4 5] [5 6] [6 4]}))

(= true (__ #{[1 2] [2 3] [3 1]
              [4 5] [5 6] [6 4] [3 4]}))

(= false (__ #{[:a :b] [:b :c] [:c :d]
               [:x :y] [:d :a] [:b :e]}))

(= true (__ #{[:a :b] [:b :c] [:c :d]
              [:x :y] [:d :a] [:b :e] [:x :a]}))
