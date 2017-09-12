;;;;; Solution 98. Equivalence Class

;; A function f defined on a domain D induces an equivalence relation on D, as follows: a is equivalent to b with respect to f if and only if (f a) is equal to (f b).
;; Write a function with arguments f and D that computes the equivalence classes of D with respect to f.



(defn equivalent-classes
  [fun col]
  (apply hash-set (for [[key val] (group-by fun col)] (apply hash-set val)))
)

(def __ equivalent-classes)

;tests

(= (__ #(* % %) #{-2 -1 0 1 2})
   #{#{0} #{1 -1} #{2 -2}})

(= (__ #(rem % 3) #{0 1 2 3 4 5 })
   #{#{0 3} #{1 4} #{2 5}})

(= (__ identity #{0 1 2 3 4})
   #{#{0} #{1} #{2} #{3} #{4}})

(= (__ (constantly true) #{0 1 2 3 4})
   #{#{0 1 2 3 4}})

