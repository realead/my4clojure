;;;;Solution for 153. Pairwise disjoint sets

;; Given a set of sets, create a function which returns true if no two of those sets have any elements in common1 and false otherwise.
;; Some of the test cases are a bit tricky, so pay a little more attention to them.

;; 1Such sets are usually called pairwise disjoint or mutually disjoint.


(defn pairwise-disjoint?
     ([sets] (pairwise-disjoint?  sets #{}))
     ([sets all]
      (if (empty? sets)
             true
             (if (empty? (clojure.set/intersection all (first sets)))
                (recur (rest sets) (clojure.set/union all (first sets)))
                false
             )
      )
     )
 )


(def __ pairwise-disjoint?)
;tests

(= (__ #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}})
   true)


(= (__ #{#{:a :b :c :d :e}
         #{:a :b :c :d}
         #{:a :b :c}
         #{:a :b}
         #{:a}})
   false)


(= (__ #{#{[1 2 3] [4 5]}
         #{[1 2] [3 4 5]}
         #{[1] [2] 3 4 5}
         #{1 2 [3 4] [5]}})
   true)


(= (__ #{#{'a 'b}
         #{'c 'd 'e}
         #{'f 'g 'h 'i}
         #{''a ''c ''f}})
   true)


(= (__ #{#{'(:x :y :z) '(:x :y) '(:z) '()}
         #{#{:x :y :z} #{:x :y} #{:z} #{}}
         #{'[:x :y :z] [:x :y] [:z] [] {}}})
   false)


(= (__ #{#{(= "true") false}
         #{:yes :no}
         #{(class 1) 0}
         #{(symbol "true") 'false}
         #{(keyword "yes") ::no}
         #{(class '1) (int \0)}})
   false)


(= (__ #{#{distinct?}
         #{#(-> %) #(-> %)}
         #{#(-> %) #(-> %) #(-> %)}
         #{#(-> %) #(-> %) #(-> %)}})
   true)

(= (__ #{#{(#(-> *)) + (quote mapcat) #_ nil}
         #{'+ '* mapcat (comment mapcat)}
         #{(do) set contains? nil?}
         #{, , , #_, , empty?}})
   false)

