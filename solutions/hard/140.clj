;;; Solution for 140. Veitch, Please!


;; Create a function which accepts as input a boolean algebra function in the form of a set of sets, where the inner sets are collections of symbols
;;    corresponding to the input boolean variables which satisfy the function (the inputs of the inner sets are conjoint, and the sets themselves are disjoint... also known as canonical minterms).
;;    Note: capitalized symbols represent truth, and lower-case symbols represent negation of the inputs.
;;          Your function must return the minimal function which is logically equivalent to the input.

;;PS — You may want to give this a read before proceeding: K-Maps

;;This is only a heuristic good enough to pass, I'm not sure it really works...

(defn pow
    [base exp]
    (loop [res 1
           exp exp]
        (if (= 0 exp)
            res
            (recur (* res base) (dec exp))
        )
    )
)
(pow 0 3)
(pow 0 0)
(pow 1 44)
(pow 2 5)

(defn determined?
    [sset N all]
    (= (pow 2 (- N (count sset))) (count (filter #(clojure.set/subset? sset %1) all)))
)

(def example #{#{'a 'B 'C 'd}
         #{'A 'b 'c 'd}
         #{'A 'b 'c 'D}
         #{'A 'b 'C 'd}
         #{'A 'b 'C 'D}
         #{'A 'B 'c 'd}
         #{'A 'B 'c 'D}
         #{'A 'B 'C 'd}})

(determined? #{'A  'c} 4 example)
(determined? #{'A 'b} 4 example)
(determined? #{'A 'a} 4 example)

(defn create-all-subsets
    [coll]
    (if (empty? coll)
        '(())
        (let [more (create-all-subsets (rest coll))]
            (concat more (map #(conj % (first coll)) more))
        )
    )
)


(create-all-subsets [1 2])

(defn find-candidates
  [coll]
    (let [N (count (first coll))]
     (into #{} (for [rule coll
            subset (create-all-subsets rule)
            :when (determined? subset N coll)]
            (set subset)
         )
     )
    )
)

(find-candidates #{#{'A 'B} #{'A 'b}})
(def any? (complement not-any?))
(defn dominated?
    [s all]
    (any? #(and (clojure.set/subset? % s) (not= % s)) all)
)

(dominated? #{'a 'b} #{#{'a}})
(dominated? #{'a 'b} [#{'c 'b}])

(defn simplify
  [rules]
    (let [candidates (find-candidates rules)]
       (set (filter #((complement dominated?) % candidates) candidates))
    )
)

(simplify example)
(simplify  #{#{'A 'B 'C 'D}
         #{'A 'B 'C 'd}})

; simplify is not enough for the first example, use brute force to simplify even further
(defn covered?
    [input simplified]
    (any?  #(clojure.set/subset? % input) simplified)
)

(any? even? #{1 3 5 4})
(covered? #{'a 'b} #{#{'a}})

(defn all-covered?
  [inputs simplified]
     (every? #(covered? % simplified) inputs)
)

(all-covered? example (simplify example))

(defn find-k-maps
    [rules]
    (let [simplified (simplify rules)]
      (set (apply min-key count (filter #(all-covered? rules %) (create-all-subsets simplified))))
    )
)


(find-k-maps example)
(find-k-maps  #{#{'A 'B 'C 'D}
         #{'A 'B 'C 'd}})


(def __ find-k-maps)
;tests

(= (__ #{#{'a 'B 'C 'd}
         #{'A 'b 'c 'd}
         #{'A 'b 'c 'D}
         #{'A 'b 'C 'd}
         #{'A 'b 'C 'D}
         #{'A 'B 'c 'd}
         #{'A 'B 'c 'D}
         #{'A 'B 'C 'd}})
   #{#{'A 'c}
     #{'A 'b}
     #{'B 'C 'd}})


(= (__ #{#{'A 'B 'C 'D}
         #{'A 'B 'C 'd}})
   #{#{'A 'B 'C}})


(= (__ #{#{'a 'b 'c 'd}
         #{'a 'B 'c 'd}
         #{'a 'b 'c 'D}
         #{'a 'B 'c 'D}
         #{'A 'B 'C 'd}
         #{'A 'B 'C 'D}
         #{'A 'b 'C 'd}
         #{'A 'b 'C 'D}})
   #{#{'a 'c}
     #{'A 'C}})

(= (__ #{#{'a 'b 'c}
         #{'a 'B 'c}
         #{'a 'b 'C}
         #{'a 'B 'C}})
   #{#{'a}})


(= (__ #{#{'a 'B 'c 'd}
         #{'A 'B 'c 'D}
         #{'A 'b 'C 'D}
         #{'a 'b 'c 'D}
         #{'a 'B 'C 'D}
         #{'A 'B 'C 'd}})
   #{#{'a 'B 'c 'd}
     #{'A 'B 'c 'D}
     #{'A 'b 'C 'D}
     #{'a 'b 'c 'D}
     #{'a 'B 'C 'D}
     #{'A 'B 'C 'd}})

(= (__ #{#{'a 'b 'c 'd}
         #{'a 'B 'c 'd}
         #{'A 'B 'c 'd}
         #{'a 'b 'c 'D}
         #{'a 'B 'c 'D}
         #{'A 'B 'c 'D}})
   #{#{'a 'c}
     #{'B 'c}})

(= (__ #{#{'a 'B 'c 'd}
         #{'A 'B 'c 'd}
         #{'a 'b 'c 'D}
         #{'a 'b 'C 'D}
         #{'A 'b 'c 'D}
         #{'A 'b 'C 'D}
         #{'a 'B 'C 'd}
         #{'A 'B 'C 'd}})
   #{#{'B 'd}
     #{'b 'D}})


(= (__ #{#{'a 'b 'c 'd}
         #{'A 'b 'c 'd}
         #{'a 'B 'c 'D}
         #{'A 'B 'c 'D}
         #{'a 'B 'C 'D}
         #{'A 'B 'C 'D}
         #{'a 'b 'C 'd}
         #{'A 'b 'C 'd}})
   #{#{'B 'D}
     #{'b 'd}})
